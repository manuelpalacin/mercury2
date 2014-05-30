package edu.upf.nets.mercury.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
		
		Query query = new Query( Criteria.
				where("dst").is(dst)
				);
		query.fields().exclude("tracerouteIpAttemptIds").exclude("tracerouteASHops").exclude("tracerouteASRelationships");
		return executeStatsQuery(query);

	}

	@Override
	public List<TracerouteASStats> getTracerouteASStatsByDstAS(
			int dstAS) {
			
		Query query = new Query( Criteria.
				where("dstAS").is(dstAS)
				);
		query.fields().exclude("tracerouteIpAttemptIds").exclude("tracerouteASHops").exclude("tracerouteASRelationships");
		
		return executeStatsQuery(query);
	}

	@Override
	public List<TracerouteASStats> getTracerouteASStatsBySrcAS(int srcAS) {
			
		Query query = new Query( Criteria.
				where("srcAS").is(srcAS)
				);
		query.fields().exclude("tracerouteIpAttemptIds").exclude("tracerouteASHops").exclude("tracerouteASRelationships");
		
		return executeStatsQuery(query);
	}

	@Override
	public List<TracerouteASStats> getTracerouteASStatsByDstCity(
			String dstCity, String dstCountry) {
		
		Query query = new Query( Criteria.
				where("dstCity").is(dstCity)
				.and("dstCountry").gte(dstCountry)
				);
		query.fields().exclude("tracerouteIpAttemptIds").exclude("tracerouteASHops").exclude("tracerouteASRelationships");
		
		return executeStatsQuery(query);
	}

	@Override
	public List<TracerouteASStats> getTracerouteASStatsByDstCountry(
			String dstCountry) {
		
		Query query = new Query( Criteria.
				where("dstCountry").is(dstCountry)
				);
		query.fields().exclude("tracerouteIpAttemptIds").exclude("tracerouteASHops").exclude("tracerouteASRelationships");
		
		return executeStatsQuery(query);
	}

	@Override
	public List<TracerouteASStats> getTracerouteASStatsBySrcCity(
			String srcCity, String srcCountry) {
	
		Query query = new Query( Criteria.
				where("srcCity").is(srcCity)
				.and("srcCountry").gte(srcCountry)
				);
		query.fields().exclude("tracerouteIpAttemptIds").exclude("tracerouteASHops").exclude("tracerouteASRelationships");
		
		return executeStatsQuery(query);
	}

	@Override
	public List<TracerouteASStats> getTracerouteASStatsBySrcCountry(
			String srcCountry) {

		Query query = new Query( Criteria.
				where("srcCountry").is(srcCountry)
				);
		query.fields().exclude("tracerouteIpAttemptIds").exclude("tracerouteASHops").exclude("tracerouteASRelationships");
		
		return executeStatsQuery(query);
	}
	
	
	
	
	private List<TracerouteASStats> executeStatsQuery(Query query){
		
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
