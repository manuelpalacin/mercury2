package edu.upf.nets.mercury.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.maxmind.geoip.Location;

@Document(collection = "ip2asmapping")
public class Ip2ASMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private String id;
	@Indexed
	private int as;
	private String asName;
	@Indexed
	private long rangeLow;
	@Indexed
	private long rangeHigh;
	@Indexed
	private long numIps;
	private String prefix;
	private int ixpParticipant;
	private String ixpParticipantName;
	private Date timeStamp;
	private String type;
	@Transient
	private String ip;
	@Transient
	private Location location;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAs() {
		return as;
	}
	public void setAs(int as) {
		this.as = as;
	}
	public String getAsName() {
		return asName;
	}
	public void setAsName(String asName) {
		this.asName = asName;
	}
	public long getRangeLow() {
		return rangeLow;
	}
	public void setRangeLow(long rangeLow) {
		this.rangeLow = rangeLow;
	}
	public long getRangeHigh() {
		return rangeHigh;
	}
	public void setRangeHigh(long rangeHigh) {
		this.rangeHigh = rangeHigh;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public int getIxpParticipant() {
		return ixpParticipant;
	}
	public void setIxpParticipant(int ixpParticipant) {
		this.ixpParticipant = ixpParticipant;
	}
	
	public String getIxpParticipantName() {
		return ixpParticipantName;
	}
	public void setIxpParticipantName(String ixpParticipantName) {
		this.ixpParticipantName = ixpParticipantName;
	}
	public long getNumIps() {
		return numIps;
	}
	public void setNumIps(long numIps) {
		this.numIps = numIps;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
		

}
