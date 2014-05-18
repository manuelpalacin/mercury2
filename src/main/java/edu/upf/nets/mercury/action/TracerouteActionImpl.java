package edu.upf.nets.mercury.action;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.googlecode.ehcache.annotations.Cacheable;
import com.opensymphony.xwork2.ActionSupport;

import edu.upf.nets.mercury.dao.TracerouteDao;
import edu.upf.nets.mercury.pojo.data.TracerouteAS;

@Controller("tracerouteAction")
public class TracerouteActionImpl extends ActionSupport implements TracerouteAction{
	
	private static final long serialVersionUID = 882281580009117862L;
	private static final Logger log = Logger.getLogger(TracerouteActionImpl.class.getName());
	private Gson gson = new Gson();
	
	private TracerouteAS tracerouteAS;
	private List<TracerouteAS> tracerouteASes;
	private String id;
	private String tracerouteASHopsJson;
	private String tracerouteASRelationshipsJson;
	
	@Autowired
	TracerouteDao tracerouteDao;
	
	
	@Cacheable(cacheName = "getTraceroutesCache")
	@Override
	public String getLastTraceroutes() {
		
		this.tracerouteASes = tracerouteDao.getTracerouteASesMin(10000);
		
		return SUCCESS;
	}

	@Override
	public String getTraceroute() {
		
		tracerouteAS = tracerouteDao.getTracerouteAS(id);
		tracerouteASHopsJson = gson.toJson(tracerouteAS.getTracerouteASHops());
		tracerouteASRelationshipsJson = gson.toJson(tracerouteAS.getTracerouteASRelationships());
				
		return SUCCESS;
	}

	@Override
	public String getASTracerouteStatsByDestination() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getASTracerouteStatsByDestinationAS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getASTracerouteStatsByOriginAS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getASTracerouteStatsByDestinationCity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getASTracerouteStatsByDestinationCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getASTracerouteStatsByOriginCity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getASTracerouteStatsByOriginCountry() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	/*
	*
	*	GETTERs and SETTERs
	*
	*/
	
	public List<TracerouteAS> getTracerouteASes() {
		return tracerouteASes;
	}

	public void setTracerouteASes(List<TracerouteAS> tracerouteASes) {
		this.tracerouteASes = tracerouteASes;
	}

	public TracerouteAS getTracerouteAS() {
		return tracerouteAS;
	}

	public void setTracerouteAS(TracerouteAS tracerouteAS) {
		this.tracerouteAS = tracerouteAS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTracerouteASHopsJson() {
		return tracerouteASHopsJson;
	}

	public void setTracerouteASHopsJson(String tracerouteASHopsJson) {
		this.tracerouteASHopsJson = tracerouteASHopsJson;
	}

	public String getTracerouteASRelationshipsJson() {
		return tracerouteASRelationshipsJson;
	}

	public void setTracerouteASRelationshipsJson(
			String tracerouteASRelationshipsJson) {
		this.tracerouteASRelationshipsJson = tracerouteASRelationshipsJson;
	}	
	

}
