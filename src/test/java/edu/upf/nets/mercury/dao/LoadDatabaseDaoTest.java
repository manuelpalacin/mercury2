package edu.upf.nets.mercury.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.upf.nets.mercury.pojo.ASInfo;
import edu.upf.nets.mercury.pojo.Ip2ASMapping;
import edu.upf.nets.mercury.util.IpAddressValidator;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class LoadDatabaseDaoTest {

	private static final Logger log = Logger.getLogger(LoadDatabaseDaoTest.class.getName());
	private static Connection connection = null;
	private JSONArray ja = new JSONArray();
	Map<Integer, String> asNames = new HashMap<Integer, String>();
	
	@Autowired
    ApplicationContext context;
	@Autowired
	LoadDatabaseDao loadDatabaseDao;
	@Autowired
	IpAddressValidator ipAddressValidator;
	
	
	@Ignore
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	@Ignore
	@Test
	public void updateAsNames(){
		List<Ip2ASMapping> list = new ArrayList<Ip2ASMapping>();

		//First we drop mappings from database
		loadDatabaseDao.dropASInfos();
		//loadDatabaseDao.dropIp2AsnMappingsNoIxps();
		loadDatabaseDao.dropIp2AsnMappings();

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


		//Third we add the ip2as mappings
		// we download the file
		String url = "http://thyme.apnic.net/current/data-raw-table";
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
					newIp2ASMapping.setAsName(asNames.get(as));
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
				}catch (Exception e){ e.printStackTrace(); }
			}


			log.info("We have mappings: "+list.size());
			loadDatabaseDao.addIp2AsnMappings(list);

		}catch (Exception e){ e.printStackTrace();  }

	}
	
//This is an old version that obtains IP-2-AS from cidr-report	
//	@Ignore
//	@Test
//	public void loadIp2AsnMappings(){
//		
//		List<Ip2ASMapping> list = new ArrayList<Ip2ASMapping>();
//		
//		//First we drop mappings from database
//		loadDatabaseDao.dropIp2AsnMappings();
//		
//		//Second we add the mappings
//		try {
////			Scanner scanner = new Scanner(context.getResource(
////					"classpath:cidr/cidr-report-full.htm").getInputStream());
////			String line = "";
////			while (scanner.hasNextLine()) {
////				 line = line + "\n" + scanner.nextLine();
////			}
////			Document doc = Jsoup.parse(line);
//			Document doc = Jsoup.parse(context.getResource(
//					"classpath:cidr/cidr-report-full.htm").getInputStream(), "UTF-8", "");
//			
//			//"pre" elements that contains "a[name]"
//			Elements links = doc.select("pre:has(a[name])");
//			log.info("There are the following ASes: "+links.size());
//			for (Element link : links) {
//				//log.info(link.text()); //All the text
//				//log.info( link.select("a").text() ); //Only "a" attibute
//				//log.info( link.text().replace( link.select("a").text(), "") ); //All text withou "a" attribute
//				//String as = link.select("a").text().replace("AS", "");
//				String as = link.select("a").attr("name").replace("AS", "");
//				//log.info(as);
//				link.select("a").remove();
//				//link.getElementsByTag("a").remove();
//				link.select("font[color=red]").remove();
//				String info = link.text();
//				//log.info( info );
//				String asName = null;
//				String prefix = null;
//				String[] lines = info.split("\n");
//				for (int i = 0; i < lines.length; i++) {
//					
//					//This is the AS Name
//					if( lines[i].startsWith(":") ){	
//						lines[i] = lines[i].replaceFirst(":", "");
//						lines[i] = lines[i].trim();
//						asName = lines[i];
//						//log.info(lines[i]);
//					}
//					
//					//Now we get the IP prefixes
//					if ( ipAddressValidator.findIpPrefix(lines[i], 1) != null ){
//						//log.info(ipAddressValidator.findIpPrefix(lines[i], 1));
//						prefix = ipAddressValidator.findIpPrefix(lines[i], 1);
//	    				long[] range = getRange(prefix);
//	    				long rangeLow = range[1];
//	    				long rangeHigh = range[2];
//	    				
//		    	    	Ip2ASMapping ip2ASMapping = new Ip2ASMapping();
//		    	    	ip2ASMapping.setAs(Integer.parseInt(as));
//		    	    	ip2ASMapping.setAsName(asName);
//		    	    	ip2ASMapping.setIxpParticipant(0);
//		    	    	ip2ASMapping.setIxpParticipantName(null);
//	    	    		ip2ASMapping.setPrefix(prefix);
//	    	    		ip2ASMapping.setRangeLow(rangeLow);
//	    	    		ip2ASMapping.setRangeHigh(rangeHigh);
//	    	    		ip2ASMapping.setNumIps(rangeHigh-rangeLow+1);
//	    	    		ip2ASMapping.setTimeStamp(new Date());
//	    	    		ip2ASMapping.setType("AS");
//	    	    		
//	    	    		//ja.put(ip2ASMapping);
//	    	    		//We save the mapping into the database
//	    	    		list.add(ip2ASMapping);
//	    	    		//loadDatabaseDao.addIp2AsnMapping(ip2ASMapping);
//					}
//				}
//			}
//			
//			log.info("We have mappings: "+list.size());
//			loadDatabaseDao.addIp2AsnMappings(list);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
//	@Ignore
//	@Test
//	public void updateIp2AsnMappings(){
//		
//		//First we download the file
//		String url = "http://www.cidr-report.org/as2.0/as-prefixes.txt";
//		try {
//			
//			Scanner scanner = HTTPGetCommand(url);
//			String line = "";
//			String as = "";
//			while (scanner.hasNextLine()) {
//				line = scanner.nextLine();
//				if(line.startsWith("AS")){
//					as = line.split(" ")[0]; //The first token is the ASNumber
//				} else {
//					line = line.trim();
//					if(line.startsWith("+")){
//						//Now we get the IP prefixes
//						if ( ipAddressValidator.findIpPrefix(line, 2) != null ){
//							log.info(as + " + " +  ipAddressValidator.findIpPrefix(line, 2));
//						}
//					} else if(line.startsWith("-")){
//						//Now we get the IP prefixes
//						if ( ipAddressValidator.findIpPrefix(line, 2) != null ){
//							log.info(as + " - " +  ipAddressValidator.findIpPrefix(line, 2));
//						}
//					}
//				}
//			}
//			scanner.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}	

	
	@Ignore
	@Test
	public void validateIpMask(){ 
		String ip = "192.168.1.1/24";
		log.info("The ip "+ip+" is valid: "+ipAddressValidator.validate(ip));
	}
	
	@Ignore
	@Test
	public void findPrefix(){ 
		String line = "192.168.1.1/24 asdads";
		log.info("The line "+line+" is contains a prefix: "+ipAddressValidator.findIpPrefix(line, 1));
	}
	
	@Ignore
	@Test
	public void loadIxpMappings(){
	
		List<Ip2ASMapping> list = new ArrayList<Ip2ASMapping>();
		
    	Statement stmt = null;
    	ResultSet rs = null;
    	
    	openConnection();

    	try {
    	    stmt = connection.createStatement();
    	    
    	    //Request for exact match
    	    if (stmt.execute("SELECT mgmtpublics.name, peerparticipants.name,  peerparticipantspublics.local_asn, peerparticipantspublics.local_ipaddr " +
    	    		"FROM peerparticipantspublics " +
    	    		"INNER JOIN mgmtpublics ON mgmtpublics.id = peerparticipantspublics.public_id " +
    	    		"INNER JOIN peerparticipants ON peerparticipants.id = peerparticipantspublics.participant_id " +
    	    		"ORDER BY mgmtpublics.id, peerparticipants.id ")) {
    	    	rs = stmt.getResultSet();
    	    }
    	    while (rs.next()){
    	    	//First we check if there is ipv4 and we validate it
    	    	if(rs.getObject("peerparticipantspublics.local_ipaddr") != null){
    	    		String prefix = (String)rs.getObject("peerparticipantspublics.local_ipaddr");
    	    		boolean valid = false;
    	    		long rangeLow = 0;
    	    		long rangeHigh = 0;
//    	    		int as = -1;
    	    		if (prefix.contains("/")){
    	    			if ( ipAddressValidator.findIpPrefix(prefix, 3) != null ){
    	    				valid = true;
    	    				long[] range = getRange(prefix);
    	    				rangeLow = range[1];
    	    				rangeHigh = range[2];
//    	    				if(loadDatabaseDao.getIp2AsnMappingByIp(rangeLow) != null)
//    	    					as = loadDatabaseDao.getIp2AsnMappingByIp(rangeLow).getAs();
    	    			}
    	    		} else {
    	    			if ( ipAddressValidator.validate(prefix) ){
    	    				valid = true;
    	    				long ipNum = ipToNum(prefix);
    	    				rangeLow = ipNum;
    	    				rangeHigh = ipNum;
//    	    				if(loadDatabaseDao.getIp2AsnMappingByIp(ipNum) != null)
//    	    					as = loadDatabaseDao.getIp2AsnMappingByIp(ipNum).getAs();
    	    			}
    	    		}
    	    		if (valid){
		    	    	Ip2ASMapping ip2ASMapping = new Ip2ASMapping();
		    	    	ip2ASMapping.setAs(Integer.parseInt(rs.getObject("peerparticipantspublics.local_asn").toString()));
		    	    	ip2ASMapping.setAsName( (String)rs.getObject("peerparticipants.name") );
		    	    	//ip2ASMapping.setIxpParticipant( Integer.parseInt(rs.getObject("peerparticipantspublics.local_asn").toString()) );
		    	    	//ip2ASMapping.setIxpParticipantName( (String)rs.getObject("peerparticipants.name") );
		    	    	ip2ASMapping.setIxpName((String)rs.getObject("mgmtpublics.name"));
		    	    	ip2ASMapping.setPrefix(prefix);
	    	    		ip2ASMapping.setRangeLow(rangeLow);
	    	    		ip2ASMapping.setRangeHigh(rangeHigh);
	    	    		ip2ASMapping.setNumIps(rangeHigh-rangeLow+1);
	    	    		ip2ASMapping.setTimeStamp(new Date());
	    	    		ip2ASMapping.setType("IXP");
	    	    		//ja.put(ip2ASMapping);
	    	    		//We save the mapping into the database
	    	    		//loadDatabaseDao.addIp2AsnMapping(ip2ASMapping);
	    	    		list.add(ip2ASMapping);
    	    		}
    	    	}
    	    }
    	    
    	    //Request for IXP prefixes
    	    if (stmt.execute("SELECT mgmtpublics.name, mgmtpublicsips.address FROM mgmtpublicsips INNER JOIN mgmtpublics ON mgmtpublics.id = mgmtpublicsips.public_id ORDER BY mgmtpublics.id")) {
    	    	rs = stmt.getResultSet();
    	    }
    	    while (rs.next()){
    	    	//First we check if there is ipv4 and we validate it
    	    	if(rs.getObject("mgmtpublicsips.address") != null){
    	    		String prefix = (String)rs.getObject("mgmtpublicsips.address");
    	    		boolean valid = false;
    	    		long rangeLow = 0;
    	    		long rangeHigh = 0;
    	    		int as = -1;
    	    		if (prefix.contains("/")){
    	    			if ( ipAddressValidator.findIpPrefix(prefix, 3) != null ){
    	    				valid = true;
    	    				long[] range = getRange(prefix);
    	    				rangeLow = range[1];
    	    				rangeHigh = range[2];
//    	    				if(loadDatabaseDao.getIp2AsnMappingByIp(rangeLow) != null)
//    	    					as = loadDatabaseDao.getIp2AsnMappingByIp(rangeLow).getAs();
    	    			}
    	    		} else {
    	    			if ( ipAddressValidator.validate(prefix) ){
    	    				valid = true;
    	    				long ipNum = ipToNum(prefix);
    	    				rangeLow = ipNum;
    	    				rangeHigh = ipNum;
//    	    				if(loadDatabaseDao.getIp2AsnMappingByIp(ipNum) != null)
//    	    					as = loadDatabaseDao.getIp2AsnMappingByIp(ipNum).getAs();
    	    			}
    	    		}
    	    		if (valid){
		    	    	Ip2ASMapping ip2ASMapping = new Ip2ASMapping();
		    	    	ip2ASMapping.setAs(as);
		    	    	ip2ASMapping.setAsName((String)rs.getObject("mgmtpublics.name"));
		    	    	//ip2ASMapping.setIxpParticipant(0);
		    	    	ip2ASMapping.setIxpName((String)rs.getObject("mgmtpublics.name"));
	    	    		ip2ASMapping.setPrefix(prefix);
	    	    		ip2ASMapping.setRangeLow(rangeLow);
	    	    		ip2ASMapping.setRangeHigh(rangeHigh);
	    	    		ip2ASMapping.setNumIps(rangeHigh-rangeLow+1);
	    	    		ip2ASMapping.setTimeStamp(new Date());
	    	    		ip2ASMapping.setType("IXP");
	    	    		//ja.put(ip2ASMapping);
	    	    		//We save the mapping into the database
	    	    		//loadDatabaseDao.addIp2AsnMapping(ip2ASMapping);
	    	    		list.add(ip2ASMapping);
    	    		}
    	    	}
    	    }
    	    
			log.info("We have IXP mappings: "+list.size());
			loadDatabaseDao.addIp2AsnMappings(list);
    	    
    	    
    	} catch (SQLException ex){
    	    // handle any errors
    	    System.out.println("SQLException: " + ex.getMessage());
    	    System.out.println("SQLState: " + ex.getSQLState());
    	    System.out.println("VendorError: " + ex.getErrorCode());
    	    
    	} finally {
    	    // it is a good idea to release
    	    // resources in a finally{} block
    	    // in reverse-order of their creation
    	    // if they are no-longer needed

    	    if (rs != null) {
    	        try {
    	            rs.close();
    	        } catch (SQLException sqlEx) { } // ignore
    	        rs = null;
    	    }
    	    if (stmt != null) {
    	        try {
    	            stmt.close();
    	        } catch (SQLException sqlEx) { } // ignore
    	        stmt = null;
    	    }
    	}
	}
	

	
	
    public static void openConnection(){
    	try {
    	    connection =
    	       DriverManager.getConnection("jdbc:mysql://localhost/peering?" +
    	                                   "user=root&password=admin");

    	} catch (SQLException ex) {
    	    // handle any errors
    	    System.out.println("SQLException: " + ex.getMessage());
    	    System.out.println("SQLState: " + ex.getSQLState());
    	    System.out.println("VendorError: " + ex.getErrorCode());
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
	
	private long ipToNum(String ip) {
		try {
			String[] ipPosition = ip.split("\\.");	
			//Step 0. Check only IPv4 addresses
			if (ipPosition.length == 4){
				// Step 1. Convert IPs into ints (32 bits).
		        long addr = 0; 
		        for (int i = 0; i < ipPosition.length; i++) { 
		            int power = 3 - i;
		            addr += ((Integer.parseInt(ipPosition[i]) % 256 * Math.pow(256, power))); 
		        } 
		        return addr;
			} else {
				return -1;
			}
		
		} catch(Exception e){
			return -1;
		}
	}
	
	
	
	

}
