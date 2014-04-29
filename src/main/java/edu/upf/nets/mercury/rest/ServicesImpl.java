package edu.upf.nets.mercury.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maxmind.geoip.Location;

import edu.upf.nets.mercury.dao.GeoIpDatabase;
import edu.upf.nets.mercury.dao.LoadDatabaseDao;
import edu.upf.nets.mercury.pojo.ASRelationship;
import edu.upf.nets.mercury.pojo.Ip2ASMapping;
import edu.upf.nets.mercury.util.IpAddressValidator;


@Component
@Path("/services")
public class ServicesImpl implements Services {

	
	@Autowired
	LoadDatabaseDao loadDatabaseDao;
	@Autowired
	GeoIpDatabase geoIpDatabase;
	@Autowired
	IpAddressValidator ipAddressValidator;
	
	@Override
	public Response myInfo(HttpServletRequest req) {
		
		try {
			//HttpSession session = req.getSession();
			String ip = req.getRemoteAddr();
			long ipNum = 2130706432L; //127.0.0.0
			long as = -1;
			String asName = "private";
			try{
				ipNum = ipAddressValidator.ipToNum(ip);
				as = loadDatabaseDao.getIp2AsnMappingByIp(ipNum).getAs();
				asName = loadDatabaseDao.getIp2AsnMappingByIp(ipNum).getAsName();
			} catch(Exception e){}
			
			String result = "{ \"ip\":\""+ip+"\", \"as\":"+as+", \"asName\":\""+asName+"\", \"timeStamp\":\""+new Date()+"\" }";
	
			return Response.status(200).entity( result ).build();	
		} catch (Exception e){
			return Response.status(200).entity( "Something went wrong Please try again" ).build();
		}
	}

	@Override
	public Response getASRelationship(HttpServletRequest req, int as0, int as1) {
		
		try {
			Integer relationship = loadDatabaseDao.getASRelationship(as0, as1);
			
			ASRelationship asRelationship = new ASRelationship();
			asRelationship.setAs0(as0);
			asRelationship.setAs1(as1);
			if(relationship == null) {
				asRelationship.setRelationship(10);
			} else {
				asRelationship.setRelationship(relationship);
			}
			return Response.status(200).entity( asRelationship ).build();
		} catch(Exception e){
			return Response.status(200).entity( "Something went wrong Please try again" ).build();
		}
	}
	
	@Override
	public Response getASRelationships(HttpServletRequest req, String pairs) {
		try {
			List<ASRelationship> resp = new ArrayList<ASRelationship>();
			String[] pairsList = pairs.split(",");
			if(pairsList.length <= 1000){
				for (String pair : pairsList) {
					
					try{
						int as0 = Integer.parseInt( pair.split("-")[0] );
						int as1 = Integer.parseInt( pair.split("-")[1] );
						
						Integer relationship = loadDatabaseDao.getASRelationship(as0, as1);
						
						ASRelationship asRelationship = new ASRelationship();
						asRelationship.setAs0(as0);
						asRelationship.setAs1(as1);
						if(relationship == null) {
							asRelationship.setRelationship(10);
						} else {
							asRelationship.setRelationship(relationship);
						}
						
						resp.add(asRelationship);
					} catch(Exception e){}
				}
				return Response.status(200).entity( resp ).build();
			} else {
				return Response.status(200).entity( "Wrong request format. You have requested more than 1000 AS rels. Please try again" ).build();
			}
		} catch(Exception e){
			return Response.status(200).entity( "Something went wrong. Please try again" ).build();
		}
	}

	@Override
	public Response getASRelationshipsPOST(HttpServletRequest req, String pairs) {
		try {
			List<ASRelationship> resp = new ArrayList<ASRelationship>();
			String[] pairsList = pairs.split(",");
			if(pairsList.length <= 1000){
				for (String pair : pairsList) {
					
					try{
						int as0 = Integer.parseInt( pair.split("-")[0] );
						int as1 = Integer.parseInt( pair.split("-")[1] );
						
						Integer relationship = loadDatabaseDao.getASRelationship(as0, as1);
						
						ASRelationship asRelationship = new ASRelationship();
						asRelationship.setAs0(as0);
						asRelationship.setAs1(as1);
						if(relationship == null) {
							asRelationship.setRelationship(10);
						} else {
							asRelationship.setRelationship(relationship);
						}
						
						resp.add(asRelationship);
					} catch(Exception e){}
				}
				return Response.status(200).entity( resp ).build();
			} else {
				return Response.status(200).entity( "Wrong request format. You have requested more than 1000 AS rels. Please try again" ).build();
			}
		} catch(Exception e){
			return Response.status(200).entity( "Something went wrong. Please try again" ).build();
		}
	}
	

	@Override
	public Response getASName(HttpServletRequest req, int as) {
		try {
			return Response.status(200).entity( loadDatabaseDao.getASName(as) ).build();
		} catch(Exception e){
			return Response.status(200).entity( "Something went wrong. Please try again" ).build();
		}
	}

	@Override
	public Response getIp2AsnMappingByIp(HttpServletRequest req, String ip) {
		try{
			long ipNum = ipAddressValidator.ipToNum(ip);
			if(ipNum == -1){
				return Response.status(200).entity( "Wrong ipv4 format. Please try again" ).build();
			} else {
				return Response.status(200).entity( loadDatabaseDao.getIp2AsnMappingByIp(ipNum) ).build();
			}
		} catch(Exception e){
			return Response.status(200).entity( "Something went wrong. Please try again" ).build();
		}
	}
	
	@Override
	public Response getIp2AsnMappingsByIp(HttpServletRequest req, String ip) {
		try {
			long ipNum = ipAddressValidator.ipToNum(ip);
			if(ipNum == -1){
				return Response.status(200).entity( "Wrong ipv4 format. Please try again" ).build();
			} else {
				return Response.status(200).entity( loadDatabaseDao.getIp2AsnMappingsByIp(ipNum) ).build();
			}
		} catch(Exception e){
			return Response.status(200).entity( "Something went wrong. Please try again" ).build();
		}
	}

	@Override
	public Response getIp2AsnMappingsByIps(HttpServletRequest req, String ips) {
		try {
			List<List<Ip2ASMapping>> resp = new ArrayList<List<Ip2ASMapping>>();
			String[] ipList = ips.split(",");
			if(ipList.length <= 1000){
				for (String ip : ipList) {
					long ipNum = ipAddressValidator.ipToNum(ip);
					if(ipNum != -1){
						resp.add(loadDatabaseDao.getIp2AsnMappingsByIp(ipNum));
					}
				}
				return Response.status(200).entity( resp ).build();
			} else {
				return Response.status(200).entity( "Wrong request format. You have requested more than 1000 IP addresses. Please try again" ).build();
			}
		} catch(Exception e){
			return Response.status(200).entity( "Something went wrong. Please try again" ).build();
		}
	}

	@Override
	public Response getIp2AsnMappingsByIpsPOST(HttpServletRequest req, String ips) {
		try {
			List<List<Ip2ASMapping>> resp = new ArrayList<List<Ip2ASMapping>>();
			String[] ipList = ips.split(",");
			if(ipList.length <= 1000){
				for (String ip : ipList) {
					long ipNum = ipAddressValidator.ipToNum(ip);
					if(ipNum != -1){
						resp.add(loadDatabaseDao.getIp2AsnMappingsByIp(ipNum));
					}
				}
				return Response.status(200).entity( resp ).build();
			} else {
				return Response.status(200).entity( "Wrong request format. You have requested more than 1000 IP addresses. Please try again" ).build();
			}
		} catch(Exception e){
			return Response.status(200).entity( "Something went wrong. Please try again" ).build();
		}
	}

	
	
	
	@Override
	public Response getIp2Geo(HttpServletRequest req, String ip) {
		try {
			long ipNum = ipAddressValidator.ipToNum(ip);
			if(ipNum == -1){
				return Response.status(200).entity( "Wrong ipv4 format. Please try again" ).build();
			} else {
				Location location = geoIpDatabase.getService().getLocation(ip);
				location.setIp(ip);
				return Response.status(200).entity( location ).build();
			}
		} catch(Exception e){
			return Response.status(200).entity( "Something went wrong. Please try again" ).build();
		}
	}

	@Override
	public Response getIps2Geo(HttpServletRequest req, String ips) {
		try {
			List<Location> resp = new ArrayList<Location>();
			String[] ipList = ips.split(",");
			if(ipList.length <= 1000){
				for (String ip : ipList) {
					long ipNum = ipAddressValidator.ipToNum(ip);
					if(ipNum != -1){
						Location location = geoIpDatabase.getService().getLocation(ip);
						location.setIp(ip);
						resp.add(location);
					}
				}
				return Response.status(200).entity( resp ).build();
			} else {
				return Response.status(200).entity( "Wrong request format. You have requested more than 1000 IP addresses. Please try again" ).build();
			}
		} catch(Exception e){
			return Response.status(200).entity( "Something went wrong. Please try again" ).build();
		}
	}

	@Override
	public Response getIps2GeoPOST(HttpServletRequest req, String ips) {
		try {
			List<Location> resp = new ArrayList<Location>();
			String[] ipList = ips.split(",");
			if(ipList.length <= 1000){
				for (String ip : ipList) {
					long ipNum = ipAddressValidator.ipToNum(ip);
					if(ipNum != -1){
						Location location = geoIpDatabase.getService().getLocation(ip);
						location.setIp(ip);
						resp.add(location);
					}
				}
				return Response.status(200).entity( resp ).build();
			} else {
				return Response.status(200).entity( "Wrong request format. You have requested more than 1000 IP addresses. Please try again" ).build();
			}
		} catch(Exception e){
			return Response.status(200).entity( "Something went wrong. Please try again" ).build();
		}
	}

	@Override
	public Response getIps2AsnGeo(HttpServletRequest req, String ips) {
		try {
			List<List<Ip2ASMapping>> resp = new ArrayList<List<Ip2ASMapping>>();
			String[] ipList = ips.split(",");
			if(ipList.length <= 1000){
				for (String ip : ipList) {
					long ipNum = ipAddressValidator.ipToNum(ip);
					if(ipNum != -1){
						//First we find the geoLoc
						Location location = geoIpDatabase.getService().getLocation(ip);
						location.setIp(ip);
						//Second we create and aux list with the ip2asn mapping and geoLoc
						List<Ip2ASMapping> ip2ASMappingsAux = new ArrayList<Ip2ASMapping>();
						List<Ip2ASMapping> ip2ASMappings = loadDatabaseDao.getIp2AsnMappingsByIp(ipNum);
						for (Ip2ASMapping ip2asMapping : ip2ASMappings) {
							ip2asMapping.setLocation(location);
							ip2ASMappingsAux.add(ip2asMapping);
						}
						resp.add(ip2ASMappingsAux);
					}
				}
				return Response.status(200).entity( resp ).build();
			} else {
				return Response.status(200).entity( "Wrong request format. You have requested more than 1000 IP addresses. Please try again" ).build();
			}
		} catch(Exception e){
			return Response.status(200).entity( "Something went wrong. Please try again" ).build();
		}
	}

	@Override
	public Response getIps2AsnGeoPOST(HttpServletRequest req, String ips) {
		try {
			List<List<Ip2ASMapping>> resp = new ArrayList<List<Ip2ASMapping>>();
			String[] ipList = ips.split(",");
			if(ipList.length <= 1000){
				for (String ip : ipList) {
					long ipNum = ipAddressValidator.ipToNum(ip);
					if(ipNum != -1){
						//First we find the geoLoc
						Location location = geoIpDatabase.getService().getLocation(ip);
						location.setIp(ip);
						//Second we create and aux list with the ip2asn mapping and geoLoc
						List<Ip2ASMapping> ip2ASMappingsAux = new ArrayList<Ip2ASMapping>();
						List<Ip2ASMapping> ip2ASMappings = loadDatabaseDao.getIp2AsnMappingsByIp(ipNum);
						for (Ip2ASMapping ip2asMapping : ip2ASMappings) {
							ip2asMapping.setLocation(location);
							ip2ASMappingsAux.add(ip2asMapping);
						}
						resp.add(ip2ASMappingsAux);
					}
				}
				return Response.status(200).entity( resp ).build();
			} else {
				return Response.status(200).entity( "Wrong request format. You have requested more than 1000 IP addresses. Please try again" ).build();
			}
		} catch(Exception e){
			return Response.status(200).entity( "Something went wrong. Please try again" ).build();
		}
	}






}