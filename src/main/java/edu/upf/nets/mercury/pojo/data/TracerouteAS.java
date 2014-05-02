package edu.upf.nets.mercury.pojo.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tracerouteas")
public class TracerouteAS {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private String id;
	@Indexed
	private int srcAS;
	private String srcASName;
	private String srcIp;
	private String srcPublicIp;
	private String srcCity;
	private String srcCountry;
	@Indexed
	private int dstAS;
	private String dstASName;
	private String dstIp;
	@Indexed
	private String dst;
	private String dstCity;
	private String dstCountry;
	private Date timeStamp;
	private List<String> tracerouteIpAttemptIds;
	private List<TracerouteASHop> tracerouteASHops;
	private List<TracerouteASRelationship> tracerouteASRelationships;
	private TracerouteASStats tracerouteASStats;
	
	public TracerouteAS(){}
	
	public TracerouteAS(int srcAS, String srcASName, String srcIp,
			String srcPublicIp, String srcCity, String srcCountry, int dstAS,
			String dstASName, String dstIp, String dst, String dstCity,
			String dstCountry, Date timeStamp) {
		super();
		this.srcAS = srcAS;
		this.srcASName = srcASName;
		this.srcIp = srcIp;
		this.srcPublicIp = srcPublicIp;
		this.srcCity = srcCity;
		this.srcCountry = srcCountry;
		this.dstAS = dstAS;
		this.dstASName = dstASName;
		this.dstIp = dstIp;
		this.dst = dst;
		this.dstCity = dstCity;
		this.dstCountry = dstCountry;
		this.timeStamp = timeStamp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getSrcIp() {
		return srcIp;
	}
	public void setSrcIp(String srcIp) {
		this.srcIp = srcIp;
	}
	public String getSrcPublicIp() {
		return srcPublicIp;
	}
	public void setSrcPublicIp(String srcPublicIp) {
		this.srcPublicIp = srcPublicIp;
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
	public int getDstAS() {
		return dstAS;
	}
	public void setDstAS(int dstAS) {
		this.dstAS = dstAS;
	}
	public String getDstASName() {
		return dstASName;
	}
	public void setDstASName(String dstASName) {
		this.dstASName = dstASName;
	}
	public String getDstIp() {
		return dstIp;
	}
	public void setDstIp(String dstIp) {
		this.dstIp = dstIp;
	}
	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = dst;
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
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public List<String> getTracerouteIpAttemptIds() {
		if(this.tracerouteIpAttemptIds == null){
			this.tracerouteIpAttemptIds  = new ArrayList<String>();
		}
		return tracerouteIpAttemptIds;
	}
	public void setTracerouteIpAttemptIds(List<String> tracerouteIpAttemptIds) {
		this.tracerouteIpAttemptIds = tracerouteIpAttemptIds;
	}
	public void addTracerouteIpAttemptId(String tracerouteIpAttemptId) {
		getTracerouteIpAttemptIds().add(tracerouteIpAttemptId);
	}
	
	public List<TracerouteASHop> getTracerouteASHops() {
		if(this.tracerouteASHops == null){
			this.tracerouteASHops = new ArrayList<TracerouteASHop>();
		}
		return tracerouteASHops;
	}
	public void setTracerouteASHops(List<TracerouteASHop> tracerouteASHops) {
		this.tracerouteASHops = tracerouteASHops;
	}
	public void addTracerouteASHops(TracerouteASHop tracerouteASHop){
		getTracerouteASHops().add(tracerouteASHop);
	}
	public List<TracerouteASRelationship> getTracerouteASRelationships() {
		if(this.tracerouteASRelationships == null){
			this.tracerouteASRelationships = new ArrayList<TracerouteASRelationship>();
		}
		return tracerouteASRelationships;
	}
	public void setTracerouteASRelationships(
			List<TracerouteASRelationship> tracerouteASRelationships) {
		this.tracerouteASRelationships = tracerouteASRelationships;
	}
	public void addTracerouteASRelationship(TracerouteASRelationship tracerouteASRelationship){
		getTracerouteASRelationships().add(tracerouteASRelationship);
	}
	public TracerouteASStats getTracerouteASStats() {
		return tracerouteASStats;
	}
	public void setTracerouteASStats(TracerouteASStats tracerouteASStats) {
		this.tracerouteASStats = tracerouteASStats;
	}

	
	
	
	
	
}
