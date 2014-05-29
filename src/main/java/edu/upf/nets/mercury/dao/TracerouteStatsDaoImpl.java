package edu.upf.nets.mercury.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import edu.upf.nets.mercury.pojo.data.TracerouteAS;
import edu.upf.nets.mercury.pojo.data.TracerouteASStats;
import edu.upf.nets.mercury.pojo.data.stats.ExtraStats;

@Repository(value="tracerouteStatsDao")
public class TracerouteStatsDaoImpl implements TracerouteStatsDao {

	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<TracerouteASStats> getTracerouteASStatsByDst(
			String dst) {

		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " dst : \""+dst+"\" }");
		
		return executeStatsQuery(query);

	}

	@Override
	public List<TracerouteASStats> getTracerouteASStatsByDstAS(
			int dstAS) {
		
		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " dstAS : "+dstAS+" }");
		
		return executeStatsQuery(query);
	}

	@Override
	public List<TracerouteASStats> getTracerouteASStatsBySrcAS(int srcAS) {
		
		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " srcAS : "+srcAS+" }");
		
		return executeStatsQuery(query);
	}

	@Override
	public List<TracerouteASStats> getTracerouteASStatsByDstCity(
			String dstCity, String dstCountry) {
		
		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " dstCity : \""+dstCity+"\", dstCountry : \""+dstCountry+"\" }");
		
		return executeStatsQuery(query);
	}

	@Override
	public List<TracerouteASStats> getTracerouteASStatsByDstCountry(
			String dstCountry) {
		
		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " dstCountry : \""+dstCountry+"\" }");
		
		return executeStatsQuery(query);
	}

	@Override
	public List<TracerouteASStats> getTracerouteASStatsBySrcCity(
			String srcCity, String srcCountry) {
		
		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " srcCity : \""+srcCity+"\", srcCountry : \""+srcCountry+"\" }");
		
		return executeStatsQuery(query);
	}

	@Override
	public List<TracerouteASStats> getTracerouteASStatsBySrcCountry(
			String srcCountry) {
		
		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " srcCountry : \""+srcCountry+"\" }");
		
		return executeStatsQuery(query);
	}
	
	
	
	
	private List<TracerouteASStats> executeStatsQuery(BasicQuery query){
		
		List<TracerouteASStats> stats = new ArrayList<TracerouteASStats>();
		List<TracerouteAS> tracerouteASes = mongoTemplate.find( query, TracerouteAS.class);
		for (TracerouteAS tracerouteAS : tracerouteASes) {
			TracerouteASStats stat = new TracerouteASStats();
			stat = tracerouteAS.getTracerouteASStats();
			ExtraStats extraStats = new ExtraStats(tracerouteAS.getSrcAS(), tracerouteAS.getSrcASName(), 
					tracerouteAS.getSrcIp(), tracerouteAS.getSrcPublicIp(),
					tracerouteAS.getSrcCity(), tracerouteAS.getSrcCountry(), 
					tracerouteAS.getDstAS(), tracerouteAS.getDstASName(),
					tracerouteAS.getDstIp(), tracerouteAS.getDst(), 
					tracerouteAS.getDstCity(), tracerouteAS.getDstCountry()
					);
			stat.setExtraStats(extraStats);
			
			stats.add(stat);
		}
		
		return stats;
		
	}
	


}
