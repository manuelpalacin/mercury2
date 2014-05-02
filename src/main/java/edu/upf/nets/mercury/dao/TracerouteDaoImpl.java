package edu.upf.nets.mercury.dao;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;





import edu.upf.nets.mercury.pojo.data.TracerouteAS;
import edu.upf.nets.mercury.pojo.data.TracerouteIp;
import edu.upf.nets.mercury.pojo.data.TracerouteSettings;


@Repository(value="tracerouteDao")
public class TracerouteDaoImpl implements TracerouteDao {

	private static final Logger log = Logger.getLogger(TracerouteDaoImpl.class.getName());
	/*
	“save” means “insert it if record do not exists” and “update it if record  exists”, or simply saveOrUpdate().
	“insert”  means “insert it if record do not exits” and “ignore it if record existed”.
	*/
	
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
    ApplicationContext context;
	
	
	@Override
	public String addTracerouteSettings(TracerouteSettings tracerouteSettings) {
		TracerouteSettings aux = mongoTemplate.findOne(
				new Query( Criteria.where("attemptsPerFlow").is(tracerouteSettings.getAttemptsPerFlow()).
						and("flowCount").gte(tracerouteSettings.getFlowCount()).
						and("minHops").gte(tracerouteSettings.getMinHops()).
						and("maxHops").gte(tracerouteSettings.getMaxHops()).
						and("attemptDelay").gte(tracerouteSettings.getAttemptDelay()).
						and("hopTimeout").gte(tracerouteSettings.getHopTimeout()).
						and("minPort").gte(tracerouteSettings.getMinPort()).
						and("maxPort").gte(tracerouteSettings.getMaxPort()).
						and("dataLength").gte(tracerouteSettings.getDataLength())
						
						), 
						TracerouteSettings.class);
		if(aux == null){
			mongoTemplate.save(tracerouteSettings);
			return null;
		} else {
			return aux.getId();
		}

	}

	@Override
	public void removeTracerouteSettings(String tracerouteSettingsId) {
		mongoTemplate.remove( new Query(
					Criteria.where("id").is(tracerouteSettingsId)
				), TracerouteSettings.class);
	}

	@Override
	public void addTracerouteIp(TracerouteIp tracerouteIp) {
		mongoTemplate.save(tracerouteIp);
	}

	@Override
	public void removeTracerouteIp(String tracerouteIpId) {
		mongoTemplate.remove( new Query(
				Criteria.where("id").is(tracerouteIpId)
			), TracerouteIp.class);
	}

	@Override
	public List<TracerouteIp> getTracerouteIpsCustomQuery(String mongoQuery) {
		BasicQuery query = new BasicQuery( mongoQuery );
        
        //log.info(query.toString());
		List<TracerouteIp> tIps = mongoTemplate.find( query
				, TracerouteIp.class);
		
		return tIps;
	}

	
	
	@Override
	public void addTracerouteAS(TracerouteAS tracerouteIpAS) {
		mongoTemplate.save(tracerouteIpAS);
	}
	
	
	@Override
	public void addTracerouteASes(List<TracerouteAS> tracerouteIpASes) {
		mongoTemplate.insert(tracerouteIpASes, TracerouteAS.class);
		
	}

	@Override
	public void removeTracerouteAS(String tracerouteASId) {
		mongoTemplate.remove( new Query(
				Criteria.where("id").is(tracerouteASId)
			), TracerouteAS.class);
	}
	
	@Override
	public void dropTracerouteASCollection() {
		mongoTemplate.dropCollection(TracerouteAS.class);
	}
	

	@Override
	public List<TracerouteAS> getTracerouteASes(int limit) {
		return mongoTemplate.find( new Query().limit(limit), TracerouteAS.class);
	}

	@Override
	public List<TracerouteAS> getTracerouteASesBySrcAS(int srcAS) {
		
		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " tracerouteASStats.multipleASesDilemma : false, "
				+ " tracerouteASStats.multipleASesDilemmaCorrected : false,"
				+ " srcAS : "+srcAS+" }");
		
		return mongoTemplate.find( query
			, TracerouteAS.class);
		
	}

	@Override
	public List<TracerouteAS> getTracerouteASesByDstAS(int dstAS) {
		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " tracerouteASStats.multipleASesDilemma : false, "
				+ " tracerouteASStats.multipleASesDilemmaCorrected : false,"
				+ " dstAS : "+dstAS+" }");
		
		return mongoTemplate.find( query
			, TracerouteAS.class);
	}

	@Override
	public List<TracerouteAS> getTracerouteASesByDst(String dst) {
		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " tracerouteASStats.multipleASesDilemma : false, "
				+ " tracerouteASStats.multipleASesDilemmaCorrected : false,"
				+ " dst : \""+dst+"\" }");
		
		return mongoTemplate.find( query
			, TracerouteAS.class);
	}

	@Override
	public List<TracerouteAS> getTracerouteASesBySrcASAndDstAS(int srcAS,
			int dstAS) {
		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " tracerouteASStats.multipleASesDilemma : false, "
				+ " tracerouteASStats.multipleASesDilemmaCorrected : false,"
				+ " srcAS : "+srcAS+", "
				+ " dstAS : "+dstAS+" }");
		
		return mongoTemplate.find( query
			, TracerouteAS.class);
	}

	@Override
	public List<TracerouteAS> getTracerouteASesBySrcASAndDst(int srcAS,
			String dst) {
		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " tracerouteASStats.multipleASesDilemma : false, "
				+ " tracerouteASStats.multipleASesDilemmaCorrected : false,"
				+ " srcAS : "+srcAS+", "
				+ " dst : "+dst+" }");
		
		return mongoTemplate.find( query
			, TracerouteAS.class);
	}

	@Override
	public List<TracerouteAS> getTracerouteASesWithLteHops(int asHops) {
		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " tracerouteASStats.multipleASesDilemma : false, "
				+ " tracerouteASStats.multipleASesDilemmaCorrected : false,"
				+ " tracerouteASStats.asHops : { $lte: "+asHops+"} }");
		
		return mongoTemplate.find( query
			, TracerouteAS.class);
	}

	@Override
	public List<TracerouteAS> getTracerouteASesWithLteHopsBySrcAS(int asHops,
			int srcAS) {
		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " tracerouteASStats.multipleASesDilemma : false, "
				+ " tracerouteASStats.multipleASesDilemmaCorrected : false,"
				+ " tracerouteASStats.asHops : { $lte: "+asHops+"},"
				+ " srcAS : "+srcAS+" }");
		
		return mongoTemplate.find( query
			, TracerouteAS.class);
	}

	@Override
	public List<TracerouteAS> getTracerouteASesWithLteHopsByDstAS(int asHops,
			int dstAS) {
		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " tracerouteASStats.multipleASesDilemma : false, "
				+ " tracerouteASStats.multipleASesDilemmaCorrected : false,"
				+ " tracerouteASStats.asHops : { $lte: "+asHops+"},"
				+ " dstAS : "+dstAS+" }");
		
		return mongoTemplate.find( query
			, TracerouteAS.class);
	}

	@Override
	public List<TracerouteAS> getTracerouteASesWithLteHopsByDst(int asHops,
			String dst) {
//		// the query object
//		Criteria criteria = Criteria.where("tracerouteASStats.completed").is(true)
//				.and("tracerouteASStats.multipleASesDilemma").is(false)
//				.and("tracerouteASStats.multipleASesDilemmaCorrected").is(false)
//				.and("tracerouteASStats.asHops").lte(asHops)
//				.and("dst").is(dst);
//		// the field object
//		Criteria matchCriteria = Criteria.where("tracerouteASStats").elemMatch(
//				Criteria.where("asHops").lte(asHops));
//		
//		BasicQuery query = new BasicQuery(
//				criteria.getCriteriaObject(),
//				matchCriteria.getCriteriaObject()
//				);

		
		BasicQuery query = new BasicQuery("{ tracerouteASStats.completed : true, "
				+ " tracerouteASStats.multipleASesDilemma : false, "
				+ " tracerouteASStats.multipleASesDilemmaCorrected : false,"
				+ " tracerouteASStats.asHops : { $lte: "+asHops+"},"
				+ " dst : \""+dst+"\" } ");
        
        //log.info(query.toString());
		List<TracerouteAS> tASes = mongoTemplate.find( query
				, TracerouteAS.class);
		
		return tASes;
	}

	@Override
	public List<TracerouteAS> getTracerouteASesCustomQuery(String mongoQuery) {
		BasicQuery query = new BasicQuery( mongoQuery );
        
        //log.info(query.toString());
		List<TracerouteAS> tASes = mongoTemplate.find( query
				, TracerouteAS.class);
		
		return tASes;
	}



}
