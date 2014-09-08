package edu.upf.nets.mercury.action;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.googlecode.ehcache.annotations.Cacheable;
import com.opensymphony.xwork2.ActionSupport;

import edu.upf.nets.mercury.dao.TracerouteDao;
import edu.upf.nets.mercury.manager.TracerouteStatsManager;
import edu.upf.nets.mercury.pojo.data.TracerouteAS;
import edu.upf.nets.mercury.pojo.data.stats.TracerouteASAggregatedStats;

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
	private TracerouteASAggregatedStats tracerouteASAggregatedStats;
	private int srcAS;
	private String srcCity;
	private String srcCountry;
	private String dst;
	private int dstAS;
	private String dstCity;
	private String dstCountry;
	
	
	@Autowired
	TracerouteDao tracerouteDao;
	@Autowired
	TracerouteStatsManager tracerouteStatsManager;
	
	
	@Cacheable(cacheName = "getTraceroutesCache")
	@Override
	public String getLastTraceroutes() {
		
		this.tracerouteASes = tracerouteDao.getTracerouteASesMin(10000);
		
		return SUCCESS;
	}

	@Override
	public String getTraceroute() {
		
		tracerouteAS = tracerouteDao.getTracerouteAS(id);
		tracerouteAS.getTracerouteASStats().setFlagsHEX( Integer.toHexString( tracerouteAS.getTracerouteASStats().getFlags() ) );
		tracerouteASHopsJson = gson.toJson(tracerouteAS.getTracerouteASHops());
		tracerouteASRelationshipsJson = gson.toJson(tracerouteAS.getTracerouteASRelationships());
				
		return SUCCESS;
	}


	@Override
	public String getTracerouteASStatsByDst() {
		this.tracerouteASAggregatedStats = tracerouteStatsManager.getTracerouteASStatsByDst(dst, false);
		return SUCCESS;
	}

	@Override
	public String getTracerouteASStatsByDstAS() {
		this.tracerouteASAggregatedStats = tracerouteStatsManager.getTracerouteASStatsByDstAS(dstAS, false);
		return SUCCESS;
	}

	@Override
	public String getTracerouteASStatsBySrcAS() {
		this.tracerouteASAggregatedStats = tracerouteStatsManager.getTracerouteASStatsBySrcAS(srcAS, false);
		return SUCCESS;
	}

	@Override
	public String getTracerouteASStatsByDstCity() {
		this.tracerouteASAggregatedStats = tracerouteStatsManager.getTracerouteASStatsByDstCity(dstCity,dstCountry, false);
		return SUCCESS;
	}

	@Override
	public String getTracerouteASStatsByDstCountry() {
		this.tracerouteASAggregatedStats = tracerouteStatsManager.getTracerouteASStatsByDstCountry(dstCountry, false);
		return SUCCESS;
	}

	@Override
	public String getTracerouteASStatsBySrcCity() {
		this.tracerouteASAggregatedStats = tracerouteStatsManager.getTracerouteASStatsBySrcCity(srcCity,srcCountry, false);
		return SUCCESS;
	}

	@Override
	public String getTracerouteASStatsBySrcCountry() {
		this.tracerouteASAggregatedStats = tracerouteStatsManager.getTracerouteASStatsBySrcCountry(srcCountry, false);
		return SUCCESS;
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

	public TracerouteASAggregatedStats getTracerouteASAggregatedStats() {
		return tracerouteASAggregatedStats;
	}

	public void setTracerouteASAggregatedStats(
			TracerouteASAggregatedStats tracerouteASAggregatedStats) {
		this.tracerouteASAggregatedStats = tracerouteASAggregatedStats;
	}

	public int getSrcAS() {
		return srcAS;
	}

	public void setSrcAS(int srcAS) {
		this.srcAS = srcAS;
	}

	public String getSrcCity() {
		return srcCity;
	}

	public void setSrcCity(String srcCity) {
		this.srcCity = srcCity;
	}

	public String getSrcCountry() {
		return srcCountry;
	}

	public void setSrcCountry(String srcCountry) {
		this.srcCountry = srcCountry;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public int getDstAS() {
		return dstAS;
	}

	public void setDstAS(int dstAS) {
		this.dstAS = dstAS;
	}

	public String getDstCity() {
		return dstCity;
	}

	public void setDstCity(String dstCity) {
		this.dstCity = dstCity;
	}

	public String getDstCountry() {
		return dstCountry;
	}

	public void setDstCountry(String dstCountry) {
		this.dstCountry = dstCountry;
	}


	

}
