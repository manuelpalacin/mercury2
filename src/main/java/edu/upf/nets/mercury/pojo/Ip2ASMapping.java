package edu.upf.nets.mercury.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.maxmind.geoip.Location;

import edu.upf.nets.mercury.util.CustomDateSerializer;

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
	private String ixpName;
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

	public long getNumIps() {
		return numIps;
	}
	public void setNumIps(long numIps) {
		this.numIps = numIps;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
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
	public String getIxpName() {
		return ixpName;
	}
	public void setIxpName(String ixpName) {
		this.ixpName = ixpName;
	}
		

}
