package edu.upf.nets.mercury.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;


@Service(value="ipAddressValidator")
public class IpAddressValidator{
 
    private Pattern pattern;
    private Matcher matcher;
 
    private static final String IPADDRESS_PATTERN = 
		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    
    private static final String PREFIX_PATTERN = 
		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])" +
		"/([1-9] |[1-2][0-9] |3[1-2] )";

    private static final String PREFIX_PATTERN2 = 
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])" +
		"/([1-9]$|[1-2][0-9]$|3[1-2]$)";
    
    private static final String PREFIX_PATTERN3 = 
		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])" +
		"/([1-9]$|[1-2][0-9]$|3[1-2]$)";
    
    public IpAddressValidator(){
	  pattern = Pattern.compile(IPADDRESS_PATTERN);
    }
 
   /**
    * Validate ip address with regular expression
    * @param ip ip address for validation
    * @return true valid ip address, false invalid ip address
    */
    public boolean validate(final String ip){		  
	  matcher = pattern.matcher(ip);
	  return matcher.matches();	    	    
    }
    
    
    public String findIpPrefix(final String line, int pat){
    	String pattern = "";
    	if(pat == 1){
    		pattern = PREFIX_PATTERN; //Prefix at the beginning of the line
    	} else if (pat == 2){
    		pattern = PREFIX_PATTERN2; //Prefix at the end of the line
    	} else if (pat == 3){
    		pattern = PREFIX_PATTERN3; //Prefix at the beginning and end of the line
    	}
    	
    	if ( (matcher = Pattern.compile(pattern).matcher(line)).find() ){
    		String prefix = matcher.group(0).trim(); 
			return prefix;
		} else {
			return null;
		}
    }
    
	public long ipToNum(String ip) {
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
	
	public String longToIp(long i) {
		return ((i >> 24) & 0xFF) + 
                   "." + ((i >> 16) & 0xFF) + 
                   "." + ((i >> 8) & 0xFF) + 
                   "." + (i & 0xFF);
	}
	
	
	public long[] getRange(String ipWithMask){
		
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
