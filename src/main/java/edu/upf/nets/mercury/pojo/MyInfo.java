package edu.upf.nets.mercury.pojo;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import edu.upf.nets.mercury.util.CustomDateSerializer;

public class MyInfo {
	
	private String ip;
	private long as;
	private String asName;
	private Date timeStamp;
	
	public MyInfo(String ip, long as, String asName) {
		super();
		this.ip = ip;
		this.as = as;
		this.asName = asName;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public long getAs() {
		return as;
	}
	public void setAs(long as) {
		this.as = as;
	}
	public String getAsName() {
		return asName;
	}
	public void setAsName(String asName) {
		this.asName = asName;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getTimeStamp() {
		this.timeStamp = new Date();
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
	

}
