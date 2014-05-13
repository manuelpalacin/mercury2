package edu.upf.nets.mercury.task;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import edu.upf.nets.mercury.dao.LoadDatabaseDao;
import edu.upf.nets.mercury.pojo.ASInfo;
import edu.upf.nets.mercury.pojo.Ip2ASMapping;
import edu.upf.nets.mercury.util.IpAddressValidator;

@Service("cronTask")
public class CronTask {
	
	private static final Logger log = Logger.getLogger(CronTask.class.getName());
	
	@Autowired
    ApplicationContext context;
	@Autowired
	LoadDatabaseDao loadDatabaseDao;
	@Autowired
	IpAddressValidator ipAddressValidator;
	
	
	Map<Integer, String> asNames = new HashMap<Integer, String>();
	String url;

/*	
	public void updateIp2AsnMappings(){
		
		List<Ip2ASMapping> list = new ArrayList<Ip2ASMapping>();
		int removed = 0;

		
		log.info("***************************\n***************************\n***************************\n***************************\n***************************\n");
		
		//LOAD properties
		if(null == url){
			try {
				Properties prop = new Properties();
				prop.load(context.getResource(
						"classpath:cidr/cidr.properties").getInputStream());
				url = prop.getProperty("cidr.asprefixes");
			} catch (IOException e) {
				e.printStackTrace();
				log.info("Properties file failed to load");
			}
		}
		
		try {
			//First we download the file
			Scanner scanner = HTTPGetCommand(url);
			String line = "";
			String as = "";
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if(line.startsWith("AS")){
					as = line.split(" ")[0].replace("AS", ""); //The first token is the ASNumber
				} else {
					line = line.trim();
					if(line.startsWith("+")){
						//Now we get the IP prefixes
						String prefix = ipAddressValidator.findIpPrefix(line, 2);
						if ( prefix != null ){
							
							long[] range = getRange(prefix);
							long ip = range[0];
			    			long rangeLow = range[1];
			    			long rangeHigh = range[2];
							
							//log.info(as + " + " +  ipAddressValidator.findIpPrefix(line, 2));
			    			//First we check if the new range summarizes other ranges
							List<Ip2ASMapping> ip2ASMappings = loadDatabaseDao.detectMappingsInRange(rangeLow, rangeHigh);
							if(! ip2ASMappings.isEmpty()){
								//Then we remove the old ones...
								for (Ip2ASMapping ip2asMapping : ip2ASMappings) {
									loadDatabaseDao.removeIp2AsnMapping(ip2asMapping);
								}
							} else { //If not, maybe the new range is included into a wider range that must be removed
								ip2ASMappings = loadDatabaseDao.getIp2AsnMappingsByIp(ip);
								if(! ip2ASMappings.isEmpty()){
									//Then we remove the wider range
									for (Ip2ASMapping ip2asMapping : ip2ASMappings) {
										loadDatabaseDao.removeIp2AsnMapping(ip2asMapping);
									}
								}
							}
							
							//Second we add the new mapping
				    	    Ip2ASMapping newIp2ASMapping = new Ip2ASMapping();
				    	    newIp2ASMapping.setAs(Integer.parseInt(as));
				    	    newIp2ASMapping.setAsName(loadDatabaseDao.getASName(Integer.parseInt(as)));
				    	    newIp2ASMapping.setIxpParticipant(0);
				    	    newIp2ASMapping.setIxpParticipantName(null);
			    	    	newIp2ASMapping.setPrefix(prefix);
			    	    	newIp2ASMapping.setRangeLow(rangeLow);
			    	    	newIp2ASMapping.setRangeHigh(rangeHigh);
			    	    	newIp2ASMapping.setNumIps(rangeHigh-rangeLow+1);
			    	    	newIp2ASMapping.setTimeStamp(new Date());
			    	    	newIp2ASMapping.setType("AS");
			    	    	//We save the mapping into a list
			    	    	list.add(newIp2ASMapping);
							
						}
						
						
					} else if(line.startsWith("-")){
						//Now we get the IP prefixes
						String prefix = ipAddressValidator.findIpPrefix(line, 2);
						if ( prefix != null ){
							//log.info(as + " - " +  ipAddressValidator.findIpPrefix(line, 2));
							Ip2ASMapping ip2ASMapping = loadDatabaseDao.existIp2AsnMapping( Integer.parseInt(as), prefix);
							//If exist...
							if(ip2ASMapping != null){
								//We remove the old one...
								loadDatabaseDao.removeIp2AsnMapping(Integer.parseInt(as), prefix);
								removed++;
							}
						}
					}
				}
			}
			scanner.close();
			
			log.info("We have mappings: "+list.size() + " and we have removed " + removed);
			loadDatabaseDao.addIp2AsnMappings(list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
*/
	
	public void updateIp2AsnMappings(){
		List<Ip2ASMapping> list = new ArrayList<Ip2ASMapping>();

		log.info("CRON TASK: DOWNLOADING THE NEW AS MAPPINGS");
		loadDatabaseDao.dropASInfos(); //And also we update ASInfo names
		
		//We load a hashmap with AS names to accelerate the mappings
		if(asNames.isEmpty()){
			try{

				Scanner scanner = new Scanner(context.getResource(
						"classpath:cidr/asnames.txt").getInputStream());
				String line = "";
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					if(line.startsWith("AS")) {
						String[] values = line.split(" ",2);
						ASInfo asInfo = new ASInfo();
						asInfo.setNum(Integer.parseInt(values[0].trim().replace("AS", "")));
						asInfo.setName(values[1].trim());
						asNames.put( //We create a hashmap to accelerate the mapping
								Integer.parseInt(values[0].trim().replace("AS", "")), 
								values[1].trim());
						loadDatabaseDao.addASInfo(asInfo);
					}
				}
			} catch(Exception e){ e.printStackTrace();}
		}
		
		
		
		//LOAD properties
		if(null == url){
			try {
				Properties prop = new Properties();
				prop.load(context.getResource(
						"classpath:cidr/cidr.properties").getInputStream());
				url = prop.getProperty("cidr.asmappings");
			} catch (IOException e) {
				e.printStackTrace();
				log.info("Properties file failed to load");
			}
		}
		
		//Second we add the ip2as mappings
		try {

			List<String> lines = HTTPGetCommand(url);
			
			for (String line : lines) {

				try{
					String prefix = line.split("\t")[0];
					int as = Integer.parseInt(line.split("\t")[1]);
	
					long[] range = getRange(prefix);
					long ip = range[0];
					long rangeLow = range[1];
					long rangeHigh = range[2];
	
					//We add the new mapping
					Ip2ASMapping newIp2ASMapping = new Ip2ASMapping();
					newIp2ASMapping.setAs(as);
					newIp2ASMapping.setAsName( asNames.get(as) );
					//newIp2ASMapping.setIxpParticipant(0);
					//newIp2ASMapping.setIxpParticipantName(null);
					newIp2ASMapping.setIxpName(null);
					newIp2ASMapping.setPrefix(prefix);
					newIp2ASMapping.setRangeLow(rangeLow);
					newIp2ASMapping.setRangeHigh(rangeHigh);
					newIp2ASMapping.setNumIps(rangeHigh-rangeLow+1);
					newIp2ASMapping.setTimeStamp(new Date());
					newIp2ASMapping.setType("AS");
					//We save the mapping into a list
					list.add(newIp2ASMapping);
					
				} catch (Exception e){
					e.printStackTrace();
				}
			}

			//First we drop mappings from database except IXPs
			loadDatabaseDao.dropIp2AsnMappingsNoIxps();
			log.info("We have mappings: "+list.size());
			loadDatabaseDao.addIp2AsnMappings(list);

			log.info("CRON TASK: FINISH");
			
		}catch (Exception e){ 
			e.printStackTrace();  
			log.info("Something wrong donwloading or storing the information");	
		}

	}
	
	
	private List<String> HTTPGetCommand(String request) {
		try {
			URL url = new URL(request);
	
	        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	        
	        connection.setRequestMethod("GET");
	        connection.setDoInput(true);
	        connection.setDoOutput(true);        

	        try {
	        
				int status = connection.getResponseCode();
				if ((status==200) || (status==201)){
					InputStream is = connection.getInputStream();
					try {
						return IOUtils.readLines(is);
					} catch(Exception e) {
						return null;
					}
					finally {
						is.close();
					}
				}
	        } catch(Exception e) {
				return null;
			}
			finally {
				connection.disconnect();
			}
		} catch (Exception e) {
			return null;
		}
		return null; 
	}
	
	private long[] getRange(String ipWithMask){
		
		String[] ip = ipWithMask.split("/");
		String[] ipPosition = ip[0].split("\\.");	
		//Step 0. Check only IPv4 addresses
		if (ipPosition.length == 4){
			// Step 1. Convert IPs into ints (32 bits).
	        long addr = 0; 
	        for (int i = 0; i < ipPosition.length; i++) { 
	            int power = 3 - i;
	            addr += ((Integer.parseInt(ipPosition[i]) % 256 * Math.pow(256, power))); 
	        } 
			// Step 2. Get CIDR mask
			int mask = (-1) << (32 - Integer.parseInt(ip[1]));
			// Step 3. Find lowest IP address
			long rangeLow = addr & mask;
			// Step 4. Find highest IP address
			long rangeHigh = rangeLow + (~mask);
			long[] data = {addr,rangeLow,rangeHigh};
			return data;
			
		} else {
			//log.info("Error for: "+ipWithMask);
			long[] data = {0,0,0};
			return data;
		}
	}
	
}
