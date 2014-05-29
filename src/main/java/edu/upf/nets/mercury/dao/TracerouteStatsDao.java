package edu.upf.nets.mercury.dao;

import java.util.List;

import edu.upf.nets.mercury.pojo.data.TracerouteASStats;

public interface TracerouteStatsDao {
	
	public List<TracerouteASStats> getTracerouteASStatsByDst(String dst);
	
	public List<TracerouteASStats> getTracerouteASStatsByDstAS(int dstAS);
	
	public List<TracerouteASStats> getTracerouteASStatsBySrcAS(int srcAS);
	
	public List<TracerouteASStats> getTracerouteASStatsByDstCity(String dstCity, String dstCountry);
	
	public List<TracerouteASStats> getTracerouteASStatsByDstCountry(String dstCountry);
	
	public List<TracerouteASStats> getTracerouteASStatsBySrcCity(String srcCity, String srcCountry);
	
	public List<TracerouteASStats> getTracerouteASStatsBySrcCountry(String srcCountry);

}
