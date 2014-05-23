package edu.upf.nets.mercury.dao;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.xbill.DNS.DClass;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.SimpleResolver;
import org.xbill.DNS.Type;

import edu.upf.nets.mercury.pojo.ASInfo;
import edu.upf.nets.mercury.pojo.Ip2ASMapping;
import edu.upf.nets.mercury.util.IpAddressValidator;

@Repository(value="loadDatabaseDao")
public class LoadDatabaseDaoImpl implements LoadDatabaseDao {
	
	private static final Logger log = Logger.getLogger(LoadDatabaseDaoImpl.class.getName());
	/*
	“save” means “insert it if record do not exists” and “update it if record  exists”, or simply saveOrUpdate().
	“insert”  means “insert it if record do not exits” and “ignore it if record existed”.
	*/
	
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
    ApplicationContext context;
	@Autowired
	IpAddressValidator ipAddressValidator;
	
	
	private HashMap<String, Integer> asRelationships;
	
	@Override
	public void loadASRelationships() {
		asRelationships = new HashMap<String, Integer>();

		//We first load the caida as relationships file
		try {
			Scanner scanner = new Scanner(context.getResource(
					"classpath:caida/latest.as-rel.txt").getInputStream());
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (!line.startsWith("#")) {
					String[] params = line.split("\\|");
					
					asRelationships.put( params[0] + "\\|" + params[1] , Integer.parseInt(params[2]) );

					if ( Integer.parseInt(params[2]) == -1) { //If provider
						asRelationships.put( params[1] + "\\|" + params[0] , 1 ); // customer
					} else {
						asRelationships.put( params[1] + "\\|" + params[0] , Integer.parseInt(params[2]) );
					}

				}
			}
			scanner.close();
			
			
			//Now we add manual siblings
			scanner = new Scanner(context.getResource(
					"classpath:caida/siblings.txt").getInputStream());
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (!line.startsWith("#")) {
					String[] params = line.split("\\|");
					for(int i=0; i<params.length; i++ ){
						for(int j=0; j<params.length; j++ ){
							if(i != j){
								asRelationships.put( params[i] + "\\|" + params[j] , 2 ); //sibling
								asRelationships.put( params[j] + "\\|" + params[i] , 2 ); //sibling
							}
						}
					}
				}
			}
			scanner.close();
			
		} catch (IOException e) {
			// log.info("Problems opening caida relationships file");
		}
	}
	
	@Override
	public Integer getASRelationship(int as0, int as1) {
		
		//First we check if the as relationships file is loaded
		if(asRelationships == null) {
			loadASRelationships();
		}
		
		try{
			return asRelationships.get( String.valueOf(as0)  + "\\|" + String.valueOf(as1) );
		} catch (NullPointerException e){
			return null;
		}
	}

	@Override
	public void dropIp2AsnMappings() {
		mongoTemplate.dropCollection(Ip2ASMapping.class);
		
	}
	
	@Override
	public void dropIp2AsnMappingsNoIxps() {
		mongoTemplate.remove( new Query( 
				Criteria.where("type").is("AS") ), 
				Ip2ASMapping.class);
	}
	
	@Override
	public void addIp2AsnMapping(Ip2ASMapping ip2asMapping) {
		mongoTemplate.save(ip2asMapping);
		
	}


	@Override
	public void addIp2AsnMappings(List<Ip2ASMapping> list) {
		mongoTemplate.insert(list, Ip2ASMapping.class);
	}

	@Override
	public void removeIp2AsnMapping(int as, String prefix) {
		mongoTemplate.remove( new Query( 
				Criteria.where("as").is(as).
				and("prefix").is(prefix) ), 
			Ip2ASMapping.class);
	}
	
	@Override
	public void removeIp2AsnMapping(Ip2ASMapping ip2ASMapping) {
		mongoTemplate.remove(ip2ASMapping);
	}

	@Override
	public Ip2ASMapping getIp2AsnMappingByIp(long ip) {
		List<Ip2ASMapping> list = mongoTemplate.find(
				new Query( Criteria.where("rangeLow").lte(ip).
						and("rangeHigh").gte(ip)).
						with(new Sort(Sort.Direction.ASC, "numIps")), 
					Ip2ASMapping.class);
		if(!list.isEmpty()){
			for (Ip2ASMapping ip2asMapping : list) {
				//We force to return mappings in IXPs
				if (ip2asMapping.getType().equals("IXP")){
					//we include the ip address
					ip2asMapping.setIp(ipAddressValidator.longToIp(ip));
					return ip2asMapping;
				}
			}
			list.get(0).setIp(ipAddressValidator.longToIp(ip));
			return list.get(0);
			
		} else {
			return null;
		}
	}
	
	@Override
	public List<Ip2ASMapping> getIp2AsnMappingsByIp(long ip) {
		List<Ip2ASMapping> list = mongoTemplate.find(
				new Query( Criteria.where("rangeLow").lte(ip).
						and("rangeHigh").gte(ip)).
						with(new Sort(Sort.Direction.ASC, "numIps")), 
					Ip2ASMapping.class);
		
		String ipAddress = ipAddressValidator.longToIp(ip);
		
		//Fallback in case the IP is not found. We use CYMRU DNS
		if(list.isEmpty()){
			Ip2ASMapping ip2ASMapping = getAsMappingsDNS(ipAddress);
			if(ip2ASMapping != null){
				list.add(ip2ASMapping);
			}
		}
		
		
		//we include the ip address in each element
		ListIterator<Ip2ASMapping> litr = list.listIterator();
        while(litr.hasNext()){
            litr.next().setIp(ipAddress);
        }
		return list;
	}



	@Override
	public Ip2ASMapping existIp2AsnMapping(int as, String prefix) {
		return mongoTemplate.findOne( new Query( 
				Criteria.where("as").is(as).
				and("prefix").is(prefix).
				and("type").is("AS") ), 
			Ip2ASMapping.class);
	}

	@Override
	public List<Ip2ASMapping> detectMappingsInRange(long rangeLow, long rangeHigh) {
		return mongoTemplate.find(new Query( Criteria.where("rangeLow").gte(rangeLow).
				and("rangeHigh").lte(rangeHigh)).
				with(new Sort(Sort.Direction.ASC, "numIps")), 
			Ip2ASMapping.class);
	}
	
	
	@Override
	public String getASName(int as) {
		Query query = new Query(Criteria.where("num").is(as));
		query.fields().include("name").include("num");
//		Ip2ASMapping ip2ASMapping = mongoTemplate.findOne( query, Ip2ASMapping.class);
//		if(ip2ASMapping != null)
//			return ip2ASMapping.getAsName();
//		else
//			return null;
		
		ASInfo asInfo = mongoTemplate.findOne( query, ASInfo.class);
		if(asInfo != null)
			return asInfo.getName();
		else
			return null;
	}

	@Override
	public void dropASInfos() {
		mongoTemplate.dropCollection(ASInfo.class);
	}

	@Override
	public void addASInfo(ASInfo asInfo) {
		mongoTemplate.save(asInfo);
	}

	
	
	public Ip2ASMapping getAsMappingsDNS(String ip) {

		Ip2ASMapping ip2ASMapping = null;
		

				try{

					String[] octets = ip.split("\\.");
					String reversedIp = octets[3]+"."+octets[2]+"."+octets[1]+"."+octets[0];
					String query = reversedIp + ".origin.asn.cymru.com";
					String line = "";

					Lookup l = new Lookup(query, Type.TXT, DClass.IN);
					l.setResolver(new SimpleResolver());
					l.run();

					if (l.getResult() == Lookup.SUCCESSFUL){
						line = l.getAnswers()[0].rdataToString();
						line = line.substring(1, line.length()-1);
						String[] params = line.split("\\|");
						if (params.length <= 5) {
							String asName = "Not found";
							try{
								query = "AS" + params[0].trim() + ".asn.cymru.com";
								l = new Lookup(query, Type.TXT, DClass.IN);
								l.setResolver(new SimpleResolver());
								l.run();
								if (l.getResult() == Lookup.SUCCESSFUL){
									line = l.getAnswers()[0].rdataToString();
									line = line.substring(1, line.length()-1);
									String[] params2 = line.split("\\|");
									if (params2.length <= 5) {
										asName = params2[4].trim();
									}

								} else{
									asName = "Not found";
								}
							}catch(Exception e){
								asName = "Not found";
							}
							
							int as = Integer.parseInt(params[0].trim());
							String bgpPrefix = params[1].trim();
							long[] range = ipAddressValidator.getRange(bgpPrefix);
							long ipNum = range[0];
							long rangeLow = range[1];
							long rangeHigh = range[2];
							
							ip2ASMapping = new Ip2ASMapping(as, asName, rangeLow, rangeHigh, rangeHigh-rangeLow+1, bgpPrefix, null, new Date(), "AS");
							//now we save it in Mongo for future uses
							addIp2AsnMapping(ip2ASMapping);
							
						}


					} else {
						return null;
					}

				} catch(Exception e){
					return null;
				}

		
    	return ip2ASMapping;
	}


}
