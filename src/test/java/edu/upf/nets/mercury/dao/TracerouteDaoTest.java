package edu.upf.nets.mercury.dao;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.logging.Logger;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.upf.nets.mercury.pojo.data.TracerouteAS;
import edu.upf.nets.mercury.pojo.data.TracerouteASStats;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TracerouteDaoTest {
	
	private static final Logger log = Logger.getLogger(TracerouteDaoTest.class.getName());
	
	@Autowired
    ApplicationContext context;
	@Autowired
	TracerouteDao tracerouteDao;
	
	@Ignore
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void addTracerouteAS() {
		TracerouteASStats TracerouteASStats1 = 
				new TracerouteASStats(2, 2, 1, 0, 0, 0, 0, true, false, false);
		TracerouteASStats TracerouteASStats2 = 
				new TracerouteASStats(1, 2, 0, 0, 0, 0, 0, true, false, false);
		TracerouteASStats TracerouteASStats3 = 
				new TracerouteASStats(3, 1, 0, 0, 0, 0, 0, true, false, false);

		TracerouteAS tracerouteAS1 = 
				new TracerouteAS(3352, "Telefonica de España", "192.168.1.2", "81.80.5.3", 
						"Barcelona", "Spain", 10310, "Yahoo-1", "78.5.6.1", "yimg.com",
						"SunnyVale", "United States", new Date());
		
		TracerouteAS tracerouteAS2 = 
				new TracerouteAS(3352, "Telefonica de España", "192.168.1.2", "81.80.5.3", 
						"Barcelona", "Spain", 10310, "Yahoo-1", "78.5.6.1", "yimg.com",
						"SunnyVale", "United States", new Date());
		
		TracerouteAS tracerouteAS3 = 
				new TracerouteAS(3352, "Telefonica de España", "192.168.1.2", "81.80.5.3", 
						"Barcelona", "Spain", 10310, "Yahoo-1", "78.5.6.1", "yimg.com",
						"SunnyVale", "United States", new Date());
		
		tracerouteAS1.setTracerouteASStats(TracerouteASStats1);  
		tracerouteAS2.setTracerouteASStats(TracerouteASStats2);
		tracerouteAS3.setTracerouteASStats(TracerouteASStats3);

		tracerouteDao.addTracerouteAS(tracerouteAS1);
		tracerouteDao.addTracerouteAS(tracerouteAS2);
		tracerouteDao.addTracerouteAS(tracerouteAS3);
		
	}
	
	@Ignore
	@Test
	public void getTracerouteASes() {
		for (TracerouteAS tracerouteAS : tracerouteDao.getTracerouteASes(10) ) {
			log.info(" getTracerouteASes --- TRACEROTE AS DST: "+ tracerouteAS.getDst());
		} 
	}
	
	@Ignore
	@Test
	public void getTracerouteASesBySrcAS() {
		int srcAS = 3352;
		for (TracerouteAS tracerouteAS : tracerouteDao.getTracerouteASesBySrcAS(srcAS)) {
			log.info(" getTracerouteASesBySrcAS --- TRACEROTE AS SRC: "+ tracerouteAS.getSrcAS());
		} 
	}
	
	@Ignore
	@Test
	public void getTracerouteASesByDst() {
		String dst = "yimg.com";
		for (TracerouteAS tracerouteAS : tracerouteDao.getTracerouteASesByDst(dst)) {
			log.info(" getTracerouteASesByDst --- TRACEROTE AS SRC: "+ tracerouteAS.getSrcAS());
		} 
	}
	
	@Ignore
	@Test
	public void getTracerouteASesWithLteHopsByDst() {
		int asHops = 2;
		String dst = "yimg.com";
		for (TracerouteAS tracerouteAS : tracerouteDao.getTracerouteASesWithLteHopsByDst(asHops, dst)) {
			log.info(" getTracerouteASesWithLteHopsByDst --- TRACEROUTE STATS HOPS: "+ tracerouteAS.getTracerouteASStats().getAsHops() );
		} 
	}
	
	@Ignore
	@Test
	public void getTracerouteASesCustomQuery() {

		String mongoQuery = "{ "
				+ " tracerouteASStats.completed : true, "
				+ " tracerouteASStats.multipleASesDilemma : false, "
				+ " tracerouteASStats.multipleASesDilemmaCorrected : false,"
				+ " tracerouteASStats.asHops : { $lte: 1 },"
				+ " dst : \"yimg.com\" "
				+ "	} ";
		
		
		for (TracerouteAS tracerouteAS : tracerouteDao.getTracerouteASesCustomQuery(mongoQuery)) {
			log.info(" getTracerouteASesCustomQuery --- TRACEROUTE STATS HOPS: "+ tracerouteAS.getTracerouteASStats().getAsHops() );
		} 
	}
	
	
	@Ignore
	@Test
	public void dropTracerouteASCollection() {
		tracerouteDao.dropTracerouteASCollection();
		log.info("**************DROPPING TracerouteASColllection*****************");
		
	}
	

	
}
