package edu.upf.nets.mercury.pojo.data.stats;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.upf.nets.mercury.pojo.data.TracerouteASStats;


public class TracerouteASAggregatedStats {

	private String info;
	
	private int srcAS;
	private String srcASName;
	private String srcCity;
	private String srcCountry;
	private String dst;
	private int dstAS;
	private String dstASName;	
	private String dstCity;
	private String dstCountry;
	
	private long numberASTracerouteStats;
	private long numberCompletedASTracerouteStats;
	
	private float averageNumberASHops;
	private float averageNumberSiblingRelationships;
	private float averageNumberProviderRelationships;
	private float averageNumberCustomerRelationships;
	private float averageNumberPeeringRelationships;
	private float averageNumberNotFoundRelationships;
	private float averageNumberIxpInterconnectionRelationships;	
	
	private float stdeviationNumberASHops;
	private float stdeviationNumberSiblingRelationships;
	private float stdeviationNumberProviderRelationships;
	private float stdeviationNumberCustomerRelationships;
	private float stdeviationNumberPeeringRelationships;
	private float stdeviationNumberNotFoundRelationships;
	private float stdeviationNumberIxpInterconnectionRelationships;	
	
	private float medianNumberASHops;
	private float medianNumberSiblingRelationships;
	private float medianNumberProviderRelationships;
	private float medianNumberCustomerRelationships;
	private float medianNumberPeeringRelationships;
	private float medianNumberNotFoundRelationships;
	private float medianNumberIxpInterconnectionRelationships;	

	
	private float q1NumberASHops;
	private float q1NumberSiblingRelationships;
	private float q1NumberProviderRelationships;
	private float q1NumberCustomerRelationships;
	private float q1NumberPeeringRelationships;
	private float q1NumberNotFoundRelationships;
	private float q1NumberIxpInterconnectionRelationships;	

	
	private float q3NumberASHops;
	private float q3NumberSiblingRelationships;
	private float q3NumberProviderRelationships;
	private float q3NumberCustomerRelationships;
	private float q3NumberPeeringRelationships;
	private float q3NumberNotFoundRelationships;
	private float q3NumberIxpInterconnectionRelationships;	

	private float percentageCompleted;
	private Date timeStamp;
	private List<TracerouteASStats> tracerouteASStats;
	
	private List<GeoMatchingStat> citySrcMatchings;
	private List<GeoMatchingStat> cityDstMatchings;
	private List<GeoMatchingStat> countrySrcMatchings;
	private List<GeoMatchingStat> countryDstMatchings;
	private List<NetworkMatchingStat> ipSrcMatchings;
	private List<NetworkMatchingStat> ipDstMatchings;
	private List<NetworkMatchingStat> asSrcMatchings;
	private List<NetworkMatchingStat> asDstMatchings;

	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getSrcAS() {
		return srcAS;
	}

	public void setSrcAS(int srcAS) {
		this.srcAS = srcAS;
	}

	public String getSrcASName() {
		return srcASName;
	}

	public void setSrcASName(String srcASName) {
		this.srcASName = srcASName;
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

	public String getDstASName() {
		return dstASName;
	}

	public long getNumberASTracerouteStats() {
		return numberASTracerouteStats;
	}

	public void setNumberASTracerouteStats(long numberASTracerouteStats) {
		this.numberASTracerouteStats = numberASTracerouteStats;
	}

	public long getNumberCompletedASTracerouteStats() {
		return numberCompletedASTracerouteStats;
	}

	public void setNumberCompletedASTracerouteStats(
			long numberCompletedASTracerouteStats) {
		this.numberCompletedASTracerouteStats = numberCompletedASTracerouteStats;
	}
	
	public void setDstASName(String dstASName) {
		this.dstASName = dstASName;
	}

	public float getAverageNumberASHops() {
		return averageNumberASHops;
	}

	public void setAverageNumberASHops(float averageNumberASHops) {
		this.averageNumberASHops = averageNumberASHops;
	}

	public float getAverageNumberSiblingRelationships() {
		return averageNumberSiblingRelationships;
	}

	public void setAverageNumberSiblingRelationships(
			float averageNumberSiblingRelationships) {
		this.averageNumberSiblingRelationships = averageNumberSiblingRelationships;
	}

	public float getAverageNumberProviderRelationships() {
		return averageNumberProviderRelationships;
	}

	public void setAverageNumberProviderRelationships(
			float averageNumberProviderRelationships) {
		this.averageNumberProviderRelationships = averageNumberProviderRelationships;
	}

	public float getAverageNumberCustomerRelationships() {
		return averageNumberCustomerRelationships;
	}

	public void setAverageNumberCustomerRelationships(
			float averageNumberCustomerRelationships) {
		this.averageNumberCustomerRelationships = averageNumberCustomerRelationships;
	}

	public float getAverageNumberPeeringRelationships() {
		return averageNumberPeeringRelationships;
	}

	public void setAverageNumberPeeringRelationships(
			float averageNumberPeeringRelationships) {
		this.averageNumberPeeringRelationships = averageNumberPeeringRelationships;
	}

	public float getAverageNumberNotFoundRelationships() {
		return averageNumberNotFoundRelationships;
	}

	public void setAverageNumberNotFoundRelationships(
			float averageNumberNotFoundRelationships) {
		this.averageNumberNotFoundRelationships = averageNumberNotFoundRelationships;
	}

	public float getAverageNumberIxpInterconnectionRelationships() {
		return averageNumberIxpInterconnectionRelationships;
	}

	public void setAverageNumberIxpInterconnectionRelationships(
			float averageNumberIxpInterconnectionRelationships) {
		this.averageNumberIxpInterconnectionRelationships = averageNumberIxpInterconnectionRelationships;
	}


	public float getStdeviationNumberASHops() {
		return stdeviationNumberASHops;
	}

	public void setStdeviationNumberASHops(float stdeviationNumberASHops) {
		this.stdeviationNumberASHops = stdeviationNumberASHops;
	}

	public float getStdeviationNumberSiblingRelationships() {
		return stdeviationNumberSiblingRelationships;
	}

	public void setStdeviationNumberSiblingRelationships(
			float stdeviationNumberSiblingRelationships) {
		this.stdeviationNumberSiblingRelationships = stdeviationNumberSiblingRelationships;
	}

	public float getStdeviationNumberProviderRelationships() {
		return stdeviationNumberProviderRelationships;
	}

	public void setStdeviationNumberProviderRelationships(
			float stdeviationNumberProviderRelationships) {
		this.stdeviationNumberProviderRelationships = stdeviationNumberProviderRelationships;
	}

	public float getStdeviationNumberCustomerRelationships() {
		return stdeviationNumberCustomerRelationships;
	}

	public void setStdeviationNumberCustomerRelationships(
			float stdeviationNumberCustomerRelationships) {
		this.stdeviationNumberCustomerRelationships = stdeviationNumberCustomerRelationships;
	}

	public float getStdeviationNumberPeeringRelationships() {
		return stdeviationNumberPeeringRelationships;
	}

	public void setStdeviationNumberPeeringRelationships(
			float stdeviationNumberPeeringRelationships) {
		this.stdeviationNumberPeeringRelationships = stdeviationNumberPeeringRelationships;
	}

	public float getStdeviationNumberNotFoundRelationships() {
		return stdeviationNumberNotFoundRelationships;
	}

	public void setStdeviationNumberNotFoundRelationships(
			float stdeviationNumberNotFoundRelationships) {
		this.stdeviationNumberNotFoundRelationships = stdeviationNumberNotFoundRelationships;
	}

	public float getStdeviationNumberIxpInterconnectionRelationships() {
		return stdeviationNumberIxpInterconnectionRelationships;
	}

	public void setStdeviationNumberIxpInterconnectionRelationships(
			float stdeviationNumberIxpInterconnectionRelationships) {
		this.stdeviationNumberIxpInterconnectionRelationships = stdeviationNumberIxpInterconnectionRelationships;
	}

	public float getPercentageCompleted() {
		return percentageCompleted;
	}

	public void setPercentageCompleted(float percentageCompleted) {
		this.percentageCompleted = percentageCompleted;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public List<TracerouteASStats> getTracerouteASStats() {
		if(tracerouteASStats==null){
			tracerouteASStats = new ArrayList<TracerouteASStats>();
		}
		return tracerouteASStats;
	}

	public void addTracerouteASStats(TracerouteASStats tracerouteASStats) {
		getTracerouteASStats().add(tracerouteASStats);
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

	
	
	
	public List<GeoMatchingStat> getCitySrcMatchings() {
		if(citySrcMatchings==null){
			citySrcMatchings = new ArrayList<GeoMatchingStat>();
		}
		return citySrcMatchings;
	}
	public void addCitySrcMatching(GeoMatchingStat matching){
		getCitySrcMatchings().add(matching);
	}
	
	public List<GeoMatchingStat> getCityDstMatchings() {
		if(cityDstMatchings==null){
			cityDstMatchings = new ArrayList<GeoMatchingStat>();
		}
		return cityDstMatchings;
	}
	public void addCityDstMatching(GeoMatchingStat matching){
		getCityDstMatchings().add(matching);
	}
	
	public List<GeoMatchingStat> getCountrySrcMatchings() {
		if(countrySrcMatchings==null){
			countrySrcMatchings = new ArrayList<GeoMatchingStat>();
		}
		return countrySrcMatchings;
	}
	public void addCountrySrcMatching(GeoMatchingStat matching){
		getCountrySrcMatchings().add(matching);
	}
	
	public List<GeoMatchingStat> getCountryDstMatchings() {
		if(countryDstMatchings==null){
			countryDstMatchings = new ArrayList<GeoMatchingStat>();
		}
		return countryDstMatchings;
	}
	public void addCountryDstMatching(GeoMatchingStat matching){
		getCountryDstMatchings().add(matching);
	}

	public List<NetworkMatchingStat> getIpSrcMatchings() {
		if(ipSrcMatchings==null){
			ipSrcMatchings = new ArrayList<NetworkMatchingStat>();
		}
		return ipSrcMatchings;
	}
	public void addIpSrcMatching(NetworkMatchingStat matching){
		getIpSrcMatchings().add(matching);
	}

	public List<NetworkMatchingStat> getIpDstMatchings() {
		if(ipDstMatchings==null){
			ipDstMatchings = new ArrayList<NetworkMatchingStat>();
		}
		return ipDstMatchings;
	}
	public void addIpDstMatching(NetworkMatchingStat matching){
		getIpDstMatchings().add(matching);
	}
	
	public List<NetworkMatchingStat> getAsSrcMatchings() {
		if(asSrcMatchings==null){
			asSrcMatchings = new ArrayList<NetworkMatchingStat>();
		}
		return asSrcMatchings;
	}
	public void addAsSrcMatching(NetworkMatchingStat matching){
		getAsSrcMatchings().add(matching);
	}
	
	public List<NetworkMatchingStat> getAsDstMatchings() {
		if(asDstMatchings==null){
			asDstMatchings = new ArrayList<NetworkMatchingStat>();
		}
		return asDstMatchings;
	}
	public void addAsDstMatching(NetworkMatchingStat matching){
		getAsDstMatchings().add(matching);
	}

	public float getMedianNumberASHops() {
		return medianNumberASHops;
	}

	public void setMedianNumberASHops(float medianNumberASHops) {
		this.medianNumberASHops = medianNumberASHops;
	}

	public float getMedianNumberSiblingRelationships() {
		return medianNumberSiblingRelationships;
	}

	public void setMedianNumberSiblingRelationships(
			float medianNumberSiblingRelationships) {
		this.medianNumberSiblingRelationships = medianNumberSiblingRelationships;
	}

	public float getMedianNumberProviderRelationships() {
		return medianNumberProviderRelationships;
	}

	public void setMedianNumberProviderRelationships(
			float medianNumberProviderRelationships) {
		this.medianNumberProviderRelationships = medianNumberProviderRelationships;
	}

	public float getMedianNumberCustomerRelationships() {
		return medianNumberCustomerRelationships;
	}

	public void setMedianNumberCustomerRelationships(
			float medianNumberCustomerRelationships) {
		this.medianNumberCustomerRelationships = medianNumberCustomerRelationships;
	}

	public float getMedianNumberPeeringRelationships() {
		return medianNumberPeeringRelationships;
	}

	public void setMedianNumberPeeringRelationships(
			float medianNumberPeeringRelationships) {
		this.medianNumberPeeringRelationships = medianNumberPeeringRelationships;
	}

	public float getMedianNumberNotFoundRelationships() {
		return medianNumberNotFoundRelationships;
	}

	public void setMedianNumberNotFoundRelationships(
			float medianNumberNotFoundRelationships) {
		this.medianNumberNotFoundRelationships = medianNumberNotFoundRelationships;
	}

	public float getMedianNumberIxpInterconnectionRelationships() {
		return medianNumberIxpInterconnectionRelationships;
	}

	public void setMedianNumberIxpInterconnectionRelationships(
			float medianNumberIxpInterconnectionRelationships) {
		this.medianNumberIxpInterconnectionRelationships = medianNumberIxpInterconnectionRelationships;
	}

	public float getQ1NumberASHops() {
		return q1NumberASHops;
	}

	public void setQ1NumberASHops(float q1NumberASHops) {
		this.q1NumberASHops = q1NumberASHops;
	}

	public float getQ1NumberSiblingRelationships() {
		return q1NumberSiblingRelationships;
	}

	public void setQ1NumberSiblingRelationships(float q1NumberSiblingRelationships) {
		this.q1NumberSiblingRelationships = q1NumberSiblingRelationships;
	}

	public float getQ1NumberProviderRelationships() {
		return q1NumberProviderRelationships;
	}

	public void setQ1NumberProviderRelationships(float q1NumberProviderRelationships) {
		this.q1NumberProviderRelationships = q1NumberProviderRelationships;
	}

	public float getQ1NumberCustomerRelationships() {
		return q1NumberCustomerRelationships;
	}

	public void setQ1NumberCustomerRelationships(float q1NumberCustomerRelationships) {
		this.q1NumberCustomerRelationships = q1NumberCustomerRelationships;
	}

	public float getQ1NumberPeeringRelationships() {
		return q1NumberPeeringRelationships;
	}

	public void setQ1NumberPeeringRelationships(float q1NumberPeeringRelationships) {
		this.q1NumberPeeringRelationships = q1NumberPeeringRelationships;
	}

	public float getQ1NumberNotFoundRelationships() {
		return q1NumberNotFoundRelationships;
	}

	public void setQ1NumberNotFoundRelationships(float q1NumberNotFoundRelationships) {
		this.q1NumberNotFoundRelationships = q1NumberNotFoundRelationships;
	}

	public float getQ1NumberIxpInterconnectionRelationships() {
		return q1NumberIxpInterconnectionRelationships;
	}

	public void setQ1NumberIxpInterconnectionRelationships(
			float q1NumberIxpInterconnectionRelationships) {
		this.q1NumberIxpInterconnectionRelationships = q1NumberIxpInterconnectionRelationships;
	}

	public float getQ3NumberASHops() {
		return q3NumberASHops;
	}

	public void setQ3NumberASHops(float q3NumberASHops) {
		this.q3NumberASHops = q3NumberASHops;
	}

	public float getQ3NumberSiblingRelationships() {
		return q3NumberSiblingRelationships;
	}

	public void setQ3NumberSiblingRelationships(float q3NumberSiblingRelationships) {
		this.q3NumberSiblingRelationships = q3NumberSiblingRelationships;
	}

	public float getQ3NumberProviderRelationships() {
		return q3NumberProviderRelationships;
	}

	public void setQ3NumberProviderRelationships(float q3NumberProviderRelationships) {
		this.q3NumberProviderRelationships = q3NumberProviderRelationships;
	}

	public float getQ3NumberCustomerRelationships() {
		return q3NumberCustomerRelationships;
	}

	public void setQ3NumberCustomerRelationships(float q3NumberCustomerRelationships) {
		this.q3NumberCustomerRelationships = q3NumberCustomerRelationships;
	}

	public float getQ3NumberPeeringRelationships() {
		return q3NumberPeeringRelationships;
	}

	public void setQ3NumberPeeringRelationships(float q3NumberPeeringRelationships) {
		this.q3NumberPeeringRelationships = q3NumberPeeringRelationships;
	}
	
	public float getQ3NumberNotFoundRelationships() {
		return q3NumberNotFoundRelationships;
	}

	public void setQ3NumberNotFoundRelationships(float q3NumberNotFoundRelationships) {
		this.q3NumberNotFoundRelationships = q3NumberNotFoundRelationships;
	}

	public float getQ3NumberIxpInterconnectionRelationships() {
		return q3NumberIxpInterconnectionRelationships;
	}

	public void setQ3NumberIxpInterconnectionRelationships(
			float q3NumberIxpInterconnectionRelationships) {
		this.q3NumberIxpInterconnectionRelationships = q3NumberIxpInterconnectionRelationships;
	}

	
}
