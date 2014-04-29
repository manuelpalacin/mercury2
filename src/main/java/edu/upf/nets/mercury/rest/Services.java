package edu.upf.nets.mercury.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



public interface Services {


	
	@GET
	@Path("/myInfo")
	public Response myInfo(@Context HttpServletRequest req);
	
	
	@GET
	@Path("/getASRelationship/{as0}/{as1}")
	@Produces("application/json")
	public Response getASRelationship(@Context HttpServletRequest req, 
			@PathParam("as0") int as0, @PathParam("as1") int as1 );
	
	//http://localhost:8080/mercury2/api/services/getASRelationships/2-3,5-7,10-11
	@GET
	@Path("/getASRelationships/{pairs}")
	@Produces("application/json")
	public Response getASRelationships(@Context HttpServletRequest req, 
			@PathParam("pairs") String pairs );
	
	//curl --data "pairs=2-3,5-6,2589-6985" http://localhost:8080/mercury2/api/services/getASRelationshipsPOST
	@POST
	@Path("/getASRelationshipsPOST")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getASRelationshipsPOST(@Context HttpServletRequest req, 
			@FormParam("pairs") String pairs );
	
	
	@GET
	@Path("/getASName/{as}")
	@Produces("application/json")
	public Response getASName(@Context HttpServletRequest req, 
			@PathParam("as") int as );
	
	
	@GET
	@Path("/getIp2AsnMappingByIp/{ip}")
	@Produces("application/json")
	public Response getIp2AsnMappingByIp(@Context HttpServletRequest req, 
			@PathParam("ip") String ip );
	
	@GET
	@Path("/getIp2AsnMappingsByIp/{ip}")
	@Produces("application/json")
	public Response getIp2AsnMappingsByIp(@Context HttpServletRequest req, 
			@PathParam("ip") String ip );
	
	//http://localhost:8080/mercury2/api/services/getIp2AsnMappingsByIps/195.69.146.102,195.69.146.100
	@GET
	@Path("/getIp2AsnMappingsByIps/{ips}")
	@Produces("application/json")
	public Response getIp2AsnMappingsByIps(@Context HttpServletRequest req, 
			@PathParam("ips") String ips );
	
	//curl --data "ips=195.69.146.102,195.69.146.100" http://localhost:8080/mercury2/api/services/getIp2AsnMappingsByIpsPOST
	@POST
	@Path("/getIp2AsnMappingsByIpsPOST")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response getIp2AsnMappingsByIpsPOST(@Context HttpServletRequest req, 
			@FormParam("ips") String ips);
	

	
	
	@GET
	@Path("/getIp2Geo/{ip}")
	@Produces("application/json")
	public Response getIp2Geo(@Context HttpServletRequest req, 
			@PathParam("ip") String ip ); 
	
	@GET
	@Path("/getIps2Geo/{ips}")
	@Produces("application/json")
	public Response getIps2Geo(@Context HttpServletRequest req, 
			@PathParam("ips") String ips ); 
	
	@POST
	@Path("/getIps2GeoPOST")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response getIps2GeoPOST(@Context HttpServletRequest req, 
			@FormParam("ips") String ips );
	
	
	//http://localhost:8080/mercury2/api/services/getIps2AsnGeo/195.69.146.102,65.125.3.4
	@GET
	@Path("/getIps2AsnGeo/{ips}")
	@Produces("application/json")
	public Response getIps2AsnGeo(@Context HttpServletRequest req, 
			@PathParam("ips") String ips ); 
	
	@POST
	@Path("/getIps2AsnGeoPOST")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response getIps2AsnGeoPOST(@Context HttpServletRequest req, 
			@FormParam("ips") String ips );
	
	
//	@GET
//	@Path("/data/{url}/{apiKey}")
//	public Response data(@Context HttpServletRequest req,
//			@PathParam("url") String url, @PathParam("apiKey") String apiKey);
//	
//	@GET
//	@Path("/getFullDatasetsInfoJson/{apiKey}")
//	@Produces("application/json")
//	public Object getFullDatasetsInfoJson(@Context HttpServletRequest req, 
//			@PathParam("apiKey")String apiKey);
//	
//	@GET
//	@Path("/getDatasetsInfoJsonp/{apiKey}")
//	@Produces("application/javascript; charset=UTF-8") 
//	public JSONWithPadding getDatasetsInfoJsonp(@Context HttpServletRequest req,
//			@QueryParam("callback") String callback, 
//			@PathParam("apiKey") String apiKey);
//	
//	
//	@POST
//	@Path("/uploadGeoJson/{apiKey}/{datasetId}")
//	@Consumes("application/json")
//	public Response uploadGeoJson(@Context HttpServletRequest req,
//			@PathParam("apiKey") String apiKey, 
//			@PathParam("datasetId") String datasetId,
//			Object obj);
	
}
