package edu.upf.nets.mercury.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.upf.nets.mercury.dao.TracerouteStatsDao;
import edu.upf.nets.mercury.pojo.data.TracerouteASStats;
import edu.upf.nets.mercury.pojo.data.stats.GeoMatchingStat;
import edu.upf.nets.mercury.pojo.data.stats.NetworkMatchingStat;
import edu.upf.nets.mercury.pojo.data.stats.TracerouteASAggregatedStats;

@Service(value="tracerouteStatsManager")
public class TracerouteStatsManagerImpl implements TracerouteStatsManager {
	
	
	@Autowired
	TracerouteStatsDao tracerouteStatsDao;

	@Override
	public TracerouteASAggregatedStats getTracerouteASStatsByDst(
			String dst, boolean full) {

		TracerouteASAggregatedStats tracerouteASAggregatedStats = generateASTracerouteAggregationStat(
				"ASTraceroute Stats by domain destination", 
				tracerouteStatsDao.getTracerouteASStatsByDst(dst), 
				full);
		tracerouteASAggregatedStats.setDst(dst);
		
		return tracerouteASAggregatedStats;
	}


	@Override
	public TracerouteASAggregatedStats getTracerouteASStatsByDstAS(
			int dstAS, boolean full) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public TracerouteASAggregatedStats getTracerouteASStatsBySrcAS(
			int srcAS, boolean full) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public TracerouteASAggregatedStats getTracerouteASStatsByDstCity(
			String dstCity, String dstCountry, boolean full) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public TracerouteASAggregatedStats getTracerouteASStatsByDstCountry(
			String dstCountry, boolean full) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public TracerouteASAggregatedStats getTracerouteASStatsBySrcCity(
			String srcCity, String srcCountry, boolean full) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public TracerouteASAggregatedStats getTracerouteASStatsBySrcCountry(
			String srcCountry, boolean full) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

	
	private TracerouteASAggregatedStats generateASTracerouteAggregationStat(
			String info, List<TracerouteASStats> tracerouteASStats, boolean full){
		
		TracerouteASAggregatedStats tracerouteASAggregatedStats = new TracerouteASAggregatedStats();
		if(tracerouteASStats.isEmpty()){
			tracerouteASAggregatedStats.setInfo(info+": Not found");
		} else {
			tracerouteASAggregatedStats.setInfo(info);
			//First we add the list of stats
			if(full){
				tracerouteASAggregatedStats.getTracerouteASStats().addAll(tracerouteASStats);
			}
			
			//Now we add the number of stats
			long numStats = tracerouteASStats.size();
			tracerouteASAggregatedStats.setNumberASTracerouteStats(numStats);  
			long completedNumStats = 0;
			
			//Sorted sets
			TreeSet<Float> numberASHopsSet = new TreeSet<Float>(), numberSiblingRelationshipsSet = new TreeSet<Float>(),
			numberProviderRelationshipsSet = new TreeSet<Float>(), numberCustomerRelationshipsSet = new TreeSet<Float>(), 
			numberPeeringRelationshipsSet = new TreeSet<Float>(), 
			numberNotFoundRelationshipsSet = new TreeSet<Float>(), numberIxpInterconnectionRelationshipsSet = new TreeSet<Float>();

			
			//Second we compute the average and median if the tracerouteStat is completed
			for (TracerouteASStats asTracerouteStat : tracerouteASStats) {
				if(asTracerouteStat.isCompleted()){

					tracerouteASAggregatedStats.setAverageNumberASHops(
							tracerouteASAggregatedStats.getAverageNumberASHops() +
							asTracerouteStat.getAsHops());
					numberASHopsSet.add((float) asTracerouteStat.getAsHops());
					tracerouteASAggregatedStats.setAverageNumberSiblingRelationships(
							tracerouteASAggregatedStats.getAverageNumberSiblingRelationships() +
							asTracerouteStat.getS2sRels());
					numberSiblingRelationshipsSet.add((float) asTracerouteStat.getS2sRels());
					tracerouteASAggregatedStats.setAverageNumberProviderRelationships(
							tracerouteASAggregatedStats.getAverageNumberProviderRelationships() +
							asTracerouteStat.getP2cRels() );
					numberProviderRelationshipsSet.add((float) asTracerouteStat.getP2cRels() );
					tracerouteASAggregatedStats.setAverageNumberCustomerRelationships(
							tracerouteASAggregatedStats.getAverageNumberCustomerRelationships() +
							asTracerouteStat.getC2pRels());
					numberCustomerRelationshipsSet.add((float) asTracerouteStat.getC2pRels());
					tracerouteASAggregatedStats.setAverageNumberPeeringRelationships(
							tracerouteASAggregatedStats.getAverageNumberPeeringRelationships() +
							asTracerouteStat.getP2pRels());
					numberPeeringRelationshipsSet.add((float) asTracerouteStat.getP2pRels());
					tracerouteASAggregatedStats.setAverageNumberNotFoundRelationships(
							tracerouteASAggregatedStats.getAverageNumberNotFoundRelationships() +
							asTracerouteStat.getNfRels());	
					numberNotFoundRelationshipsSet.add((float) asTracerouteStat.getNfRels());
					tracerouteASAggregatedStats.setAverageNumberIxpInterconnectionRelationships(
							tracerouteASAggregatedStats.getAverageNumberIxpInterconnectionRelationships() +
							asTracerouteStat.getIxpRels());
					numberIxpInterconnectionRelationshipsSet.add((float) asTracerouteStat.getIxpRels());
					
					
					completedNumStats++;
				}
			}
			//We store the completedNumStats and its percentage
			tracerouteASAggregatedStats.setNumberCompletedASTracerouteStats(completedNumStats);
			tracerouteASAggregatedStats.setPercentageCompleted( (float)completedNumStats / (float)numStats);
			
			//We store the average
			tracerouteASAggregatedStats.setAverageNumberASHops(
					tracerouteASAggregatedStats.getAverageNumberASHops() / completedNumStats);
			tracerouteASAggregatedStats.setAverageNumberSiblingRelationships(
					tracerouteASAggregatedStats.getAverageNumberSiblingRelationships() / completedNumStats);
			tracerouteASAggregatedStats.setAverageNumberProviderRelationships(
					tracerouteASAggregatedStats.getAverageNumberProviderRelationships() / completedNumStats);
			tracerouteASAggregatedStats.setAverageNumberCustomerRelationships(
					tracerouteASAggregatedStats.getAverageNumberCustomerRelationships() / completedNumStats);
			tracerouteASAggregatedStats.setAverageNumberPeeringRelationships(
					tracerouteASAggregatedStats.getAverageNumberPeeringRelationships() / completedNumStats);			
			tracerouteASAggregatedStats.setAverageNumberNotFoundRelationships(
					tracerouteASAggregatedStats.getAverageNumberNotFoundRelationships() / completedNumStats);	
			tracerouteASAggregatedStats.setAverageNumberIxpInterconnectionRelationships(
					tracerouteASAggregatedStats.getAverageNumberIxpInterconnectionRelationships() / completedNumStats);

			
			//We store the median and quartiles Q1 and Q3 if there are completed traceroutes
			if(completedNumStats>0){
				float[] quartilesNumberASHops = getQuartiles( numberASHopsSet.toArray(new Float[numberASHopsSet.size()]) );
				tracerouteASAggregatedStats.setMedianNumberASHops(quartilesNumberASHops[0]);
				tracerouteASAggregatedStats.setQ1NumberASHops(quartilesNumberASHops[1]);
				tracerouteASAggregatedStats.setQ3NumberASHops(quartilesNumberASHops[2]);
				float[] quartilesNumberSiblingRelationships = getQuartiles(numberSiblingRelationshipsSet.toArray(new Float[numberSiblingRelationshipsSet.size()]));
				tracerouteASAggregatedStats.setMedianNumberSiblingRelationships(quartilesNumberSiblingRelationships[0]);
				tracerouteASAggregatedStats.setQ1NumberSiblingRelationships(quartilesNumberSiblingRelationships[1]);
				tracerouteASAggregatedStats.setQ3NumberSiblingRelationships(quartilesNumberSiblingRelationships[2]);
				float[] quartilesNumberProviderRelationships = getQuartiles(numberProviderRelationshipsSet.toArray(new Float[numberProviderRelationshipsSet.size()]));
				tracerouteASAggregatedStats.setMedianNumberProviderRelationships(quartilesNumberProviderRelationships[0]);
				tracerouteASAggregatedStats.setQ1NumberProviderRelationships(quartilesNumberProviderRelationships[1]);
				tracerouteASAggregatedStats.setQ3NumberProviderRelationships(quartilesNumberProviderRelationships[2]);
				float[] quartilesNumberCustomerRelationships = getQuartiles(numberCustomerRelationshipsSet.toArray(new Float[numberCustomerRelationshipsSet.size()]));
				tracerouteASAggregatedStats.setMedianNumberCustomerRelationships(quartilesNumberCustomerRelationships[0]);
				tracerouteASAggregatedStats.setQ1NumberCustomerRelationships(quartilesNumberCustomerRelationships[1]);
				tracerouteASAggregatedStats.setQ3NumberCustomerRelationships(quartilesNumberCustomerRelationships[2]);
				float[] quartilesNumberPeeringRelationships = getQuartiles(numberPeeringRelationshipsSet.toArray(new Float[numberPeeringRelationshipsSet.size()]));
				tracerouteASAggregatedStats.setMedianNumberPeeringRelationships(quartilesNumberPeeringRelationships[0]);
				tracerouteASAggregatedStats.setQ1NumberPeeringRelationships(quartilesNumberPeeringRelationships[1]);
				tracerouteASAggregatedStats.setQ3NumberPeeringRelationships(quartilesNumberPeeringRelationships[2]);
				float[] quartilesNumberNotFoundRelationships = getQuartiles(numberNotFoundRelationshipsSet.toArray(new Float[numberNotFoundRelationshipsSet.size()]));
				tracerouteASAggregatedStats.setMedianNumberNotFoundRelationships(quartilesNumberNotFoundRelationships[0]);
				tracerouteASAggregatedStats.setQ1NumberNotFoundRelationships(quartilesNumberNotFoundRelationships[1]);
				tracerouteASAggregatedStats.setQ3NumberNotFoundRelationships(quartilesNumberNotFoundRelationships[2]);
				float[] quartilesNumberIxpInterconnectionRelationships = getQuartiles(numberIxpInterconnectionRelationshipsSet.toArray(new Float[numberIxpInterconnectionRelationshipsSet.size()]));
				tracerouteASAggregatedStats.setMedianNumberIxpInterconnectionRelationships(quartilesNumberIxpInterconnectionRelationships[0]);
				tracerouteASAggregatedStats.setQ1NumberIxpInterconnectionRelationships(quartilesNumberIxpInterconnectionRelationships[1]);
				tracerouteASAggregatedStats.setQ3NumberIxpInterconnectionRelationships(quartilesNumberIxpInterconnectionRelationships[2]);

			}
			
			//Third we compute the standard deviation for completedStats
			for (TracerouteASStats asTracerouteStat : tracerouteASStats) {
				if(asTracerouteStat.isCompleted()){		
					tracerouteASAggregatedStats.setStdeviationNumberASHops(
							(float) (tracerouteASAggregatedStats.getStdeviationNumberASHops() +
							Math.pow( (asTracerouteStat.getAsHops() - tracerouteASAggregatedStats.getAverageNumberASHops()), 2)) );
					tracerouteASAggregatedStats.setStdeviationNumberSiblingRelationships(
							(float) (tracerouteASAggregatedStats.getStdeviationNumberSiblingRelationships() +
							Math.pow( (asTracerouteStat.getS2sRels() - tracerouteASAggregatedStats.getAverageNumberSiblingRelationships()) , 2))  );
					tracerouteASAggregatedStats.setStdeviationNumberProviderRelationships(
							(float) (tracerouteASAggregatedStats.getStdeviationNumberProviderRelationships() +
							Math.pow( (asTracerouteStat.getP2cRels()  - tracerouteASAggregatedStats.getAverageNumberProviderRelationships()), 2)) );
					tracerouteASAggregatedStats.setStdeviationNumberCustomerRelationships(
							(float) (tracerouteASAggregatedStats.getStdeviationNumberCustomerRelationships() +
							Math.pow( (asTracerouteStat.getC2pRels() - tracerouteASAggregatedStats.getAverageNumberCustomerRelationships()), 2)) );	
					tracerouteASAggregatedStats.setStdeviationNumberPeeringRelationships(
							(float) (tracerouteASAggregatedStats.getStdeviationNumberPeeringRelationships() +
							Math.pow( (asTracerouteStat.getP2pRels() - tracerouteASAggregatedStats.getAverageNumberPeeringRelationships()), 2)) );	
					
					tracerouteASAggregatedStats.setStdeviationNumberNotFoundRelationships(
							(float) (tracerouteASAggregatedStats.getStdeviationNumberNotFoundRelationships() +
							Math.pow( (asTracerouteStat.getNfRels() - tracerouteASAggregatedStats.getAverageNumberNotFoundRelationships()), 2)) );	
					tracerouteASAggregatedStats.setStdeviationNumberIxpInterconnectionRelationships(
							(float) (tracerouteASAggregatedStats.getStdeviationNumberIxpInterconnectionRelationships() +
							Math.pow( (asTracerouteStat.getIxpRels() - tracerouteASAggregatedStats.getAverageNumberIxpInterconnectionRelationships()) , 2)) );
				}
			
			}
			
			
			tracerouteASAggregatedStats.setStdeviationNumberASHops(
					(float) Math.sqrt(tracerouteASAggregatedStats.getStdeviationNumberASHops() / (completedNumStats-1)) );
			tracerouteASAggregatedStats.setStdeviationNumberSiblingRelationships(
					(float) Math.sqrt(tracerouteASAggregatedStats.getStdeviationNumberSiblingRelationships() / (completedNumStats-1)) );
			tracerouteASAggregatedStats.setStdeviationNumberProviderRelationships(
					(float) Math.sqrt(tracerouteASAggregatedStats.getStdeviationNumberProviderRelationships() / (completedNumStats-1)) );
			tracerouteASAggregatedStats.setStdeviationNumberCustomerRelationships(
					(float) Math.sqrt(tracerouteASAggregatedStats.getStdeviationNumberCustomerRelationships() / (completedNumStats-1)) );
			tracerouteASAggregatedStats.setStdeviationNumberPeeringRelationships(
					(float) Math.sqrt(tracerouteASAggregatedStats.getStdeviationNumberPeeringRelationships() / (completedNumStats-1)) );			
			tracerouteASAggregatedStats.setStdeviationNumberNotFoundRelationships(
					(float) Math.sqrt(tracerouteASAggregatedStats.getStdeviationNumberNotFoundRelationships() / (completedNumStats-1)) );	
			tracerouteASAggregatedStats.setStdeviationNumberIxpInterconnectionRelationships(
					(float) Math.sqrt(tracerouteASAggregatedStats.getStdeviationNumberIxpInterconnectionRelationships() / (completedNumStats-1)) );

		
			//Fourth we include information related with the Network and Geo Matchings
			SortedMap<String, Long> citySrcMatchingsMap = new TreeMap<String, Long>();
			SortedMap<String, Long> cityDstMatchingsMap = new TreeMap<String, Long>();
			SortedMap<String, Long> countrySrcMatchingsMap = new TreeMap<String, Long>();
			SortedMap<String, Long> countryDstMatchingsMap = new TreeMap<String, Long>();
			SortedMap<String, Long> ipSrcMatchingsMap = new TreeMap<String, Long>();
			SortedMap<String, Long> ipDstMatchingsMap = new TreeMap<String, Long>();
			SortedMap<Integer, Long> asSrcMatchingsMap = new TreeMap<Integer, Long>();
			SortedMap<Integer, Long> asDstMatchingsMap = new TreeMap<Integer, Long>();
			Long citySrcMatchings = new Long(0);
			Long cityDstMatchings = new Long(0);
			Long countrySrcMatchings = new Long(0);
			Long countryDstMatchings = new Long(0);
			Long ipSrcMatchings = new Long(0);
			Long ipDstMatchings = new Long(0);
			Long asSrcMatchings = new Long(0);
			Long asDstMatchings = new Long(0);
			
			for (TracerouteASStats asTracerouteStat : tracerouteASStats) {	
				if(asTracerouteStat.isCompleted()){
					
					//This is to avoid nullpointers when there is NO geolocation. We put a white-space " " for empty cities or countries
					String srcCity=asTracerouteStat.getExtraStats().getSrcCity();
					if(srcCity==null)srcCity=" ";
					String srcCountry=asTracerouteStat.getExtraStats().getSrcCountry();
					if(srcCountry==null)srcCountry=" ";
					String dstCity=asTracerouteStat.getExtraStats().getDstCity();
					if(dstCity==null)dstCity=" ";
					String dstCountry=asTracerouteStat.getExtraStats().getDstCountry();
					if(dstCountry==null)dstCountry=" ";
					
					if(citySrcMatchingsMap.get(srcCity+"/"+srcCountry) != null){
						citySrcMatchings = Long.valueOf( citySrcMatchingsMap.get(srcCity+"/"+srcCountry).longValue() + 1 );
						citySrcMatchingsMap.put(srcCity+"/"+srcCountry, citySrcMatchings);
					} else {
						citySrcMatchingsMap.put(srcCity+"/"+srcCountry, new Long(1));
					}
					
					if(cityDstMatchingsMap.get(dstCity+"/"+dstCountry) != null){
						cityDstMatchings = Long.valueOf( cityDstMatchingsMap.get(dstCity+"/"+dstCountry).longValue() + 1 ) ;
						cityDstMatchingsMap.put(dstCity+"/"+dstCountry, cityDstMatchings);
					} else {
						cityDstMatchingsMap.put(dstCity+"/"+dstCountry, new Long(1));
					}
	
					if(countrySrcMatchingsMap.get(srcCountry) != null){
						countrySrcMatchings = Long.valueOf( countrySrcMatchingsMap.get(srcCountry).longValue() + 1 );
						countrySrcMatchingsMap.put(srcCountry, countrySrcMatchings);	
					} else {
						countrySrcMatchingsMap.put(srcCountry, new Long(1));	
					}
					
					if(countryDstMatchingsMap.get(dstCountry) != null){
						countryDstMatchings = Long.valueOf( countryDstMatchingsMap.get(dstCountry).longValue() + 1 );
						countryDstMatchingsMap.put(dstCountry, countryDstMatchings);
					} else {
						countryDstMatchingsMap.put(dstCountry, new Long(1));
					}

					
					if(ipSrcMatchingsMap.get(asTracerouteStat.getExtraStats().getSrcIp()) != null){
						ipSrcMatchings = Long.valueOf( ipSrcMatchingsMap.get(asTracerouteStat.getExtraStats().getSrcIp()).longValue() + 1 );
						ipSrcMatchingsMap.put(asTracerouteStat.getExtraStats().getSrcIp(), ipSrcMatchings);
					} else {
						ipSrcMatchingsMap.put(asTracerouteStat.getExtraStats().getSrcIp(), new Long(1));
					}
				
					if(ipDstMatchingsMap.get(asTracerouteStat.getExtraStats().getDstIp()) != null){
						ipDstMatchings = Long.valueOf( ipDstMatchingsMap.get(asTracerouteStat.getExtraStats().getDstIp()).longValue() + 1 );
						ipDstMatchingsMap.put(asTracerouteStat.getExtraStats().getDstIp(), ipDstMatchings);
					} else {
						ipDstMatchingsMap.put(asTracerouteStat.getExtraStats().getDstIp(), new Long(1));
					}
	
					if(asSrcMatchingsMap.get(asTracerouteStat.getExtraStats().getSrcAS()) != null){
						asSrcMatchings = Long.valueOf( asSrcMatchingsMap.get(asTracerouteStat.getExtraStats().getSrcAS()).longValue() + 1 );
						asSrcMatchingsMap.put(asTracerouteStat.getExtraStats().getSrcAS(), asSrcMatchings);
					} else {
						asSrcMatchingsMap.put(asTracerouteStat.getExtraStats().getSrcAS(), new Long(1));
					}
			
					if(asDstMatchingsMap.get(asTracerouteStat.getExtraStats().getDstAS()) != null){
						asDstMatchings = Long.valueOf( asDstMatchingsMap.get(asTracerouteStat.getExtraStats().getDstAS()).longValue() + 1 );
						asDstMatchingsMap.put(asTracerouteStat.getExtraStats().getDstAS(), asDstMatchings);
					} else {
						asDstMatchingsMap.put(asTracerouteStat.getExtraStats().getDstAS(), new Long(1));
					}
				}
			}
			//Now we add each map info to the aggregated stat
			NetworkMatchingStat nwStat;
			GeoMatchingStat geoStat;
			for (Map.Entry<String, Long> entry : citySrcMatchingsMap.entrySet()){
				String[] pairs = entry.getKey().split("/");
				geoStat = new GeoMatchingStat();
				geoStat.setCity(pairs[0]);
				geoStat.setCountry(pairs[1]);
				geoStat.setNumber(entry.getValue());
				tracerouteASAggregatedStats.addCitySrcMatching(geoStat);
			}
			for (Map.Entry<String, Long> entry : cityDstMatchingsMap.entrySet()){
				String[] pairs = entry.getKey().split("/");
				geoStat = new GeoMatchingStat();
				geoStat.setCity(pairs[0]);
				geoStat.setCountry(pairs[1]);
				geoStat.setNumber(entry.getValue());
				tracerouteASAggregatedStats.addCityDstMatching(geoStat);
			}
			for (Map.Entry<String, Long> entry : countrySrcMatchingsMap.entrySet()){
				geoStat = new GeoMatchingStat();
				geoStat.setCountry(entry.getKey());
				geoStat.setNumber(entry.getValue());
				tracerouteASAggregatedStats.addCountrySrcMatching(geoStat);
			}
			for (Map.Entry<String, Long> entry : countryDstMatchingsMap.entrySet()){
				geoStat = new GeoMatchingStat();
				geoStat.setCountry(entry.getKey());
				geoStat.setNumber(entry.getValue());
				tracerouteASAggregatedStats.addCountryDstMatching(geoStat);
			}
			
			for (Map.Entry<String, Long> entry : ipSrcMatchingsMap.entrySet()){
				nwStat = new NetworkMatchingStat();
				nwStat.setIp(entry.getKey());
				nwStat.setNumber(entry.getValue());
				tracerouteASAggregatedStats.addIpSrcMatching(nwStat);
			}
			for (Map.Entry<String, Long> entry : ipDstMatchingsMap.entrySet()){
				nwStat = new NetworkMatchingStat();
				nwStat.setIp(entry.getKey());
				nwStat.setNumber(entry.getValue());
				tracerouteASAggregatedStats.addIpDstMatching(nwStat);
			}
			for (Map.Entry<Integer, Long> entry : asSrcMatchingsMap.entrySet()){
				nwStat = new NetworkMatchingStat();
				nwStat.setAsNumber(entry.getKey());
				nwStat.setNumber(entry.getValue());
				tracerouteASAggregatedStats.addAsSrcMatching(nwStat);
			}
			for (Map.Entry<Integer, Long> entry : asDstMatchingsMap.entrySet()){
				nwStat = new NetworkMatchingStat();
				nwStat.setAsNumber(entry.getKey());
				nwStat.setNumber(entry.getValue());
				tracerouteASAggregatedStats.addAsDstMatching(nwStat);
			}
			
			
		}
		//Finally we add the timestamp
		tracerouteASAggregatedStats.setTimeStamp(new Date());
		
		return tracerouteASAggregatedStats;
	}

	
	// Quartile function
	// the array float[] m MUST BE SORTED
	private float[] getQuartiles(Float[] m){
		float[] q = new float[3];
		//float 0: median, 1 q1, 2 q3
		float r0[] = median(m, 0, m.length);
		q[0] = r0[0];
		float r1[] = median(m, 0, (int)r0[1]);
		float r2[] = median(m, (int)r0[2], m.length);
		q[1] = r1[0];
		q[2] = r2[0];
		return q;
	}
	
	private float[] median(Float[] m, int start, int end) {
		//float 0: median, 1 leftindex, 2 rightindex
		float[] result = new float[3];
		int count = end - start;
		if(count%2==1){
			result[2]=start + (count-1)/2;
			result[1]=result[2]+1;
			result[0]=m[(int)result[2]];
		} else {
			result[1]=result[2]=start + (count/2);
			result[0]=( m[(int)result[1]-1] + m[(int)result[1]] ) / 2;
		}
		return result;
	}




}
