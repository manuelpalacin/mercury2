package edu.upf.nets.mercury.manager;

import edu.upf.nets.mercury.pojo.data.stats.TracerouteASAggregatedStats;



public interface TracerouteStatsManager {
	
	
	public TracerouteASAggregatedStats getTracerouteASStatsByDst(String dst, boolean full);
	
	public TracerouteASAggregatedStats getTracerouteASStatsByDstAS(int dstAS, boolean full);
	
	public TracerouteASAggregatedStats getTracerouteASStatsBySrcAS(int srcAS, boolean full);
	
	public TracerouteASAggregatedStats getTracerouteASStatsByDstCity(String dstCity, String dstCountry, boolean full);
	
	public TracerouteASAggregatedStats getTracerouteASStatsByDstCountry(String dstCountry, boolean full);
	
	public TracerouteASAggregatedStats getTracerouteASStatsBySrcCity(String srcCity, String srcCountry, boolean full);
	
	public TracerouteASAggregatedStats getTracerouteASStatsBySrcCountry(String srcCountry, boolean full);
	

}
