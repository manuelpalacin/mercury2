package edu.upf.nets.mercury.pojo.data;

import java.util.Date;

public class TracerouteIpHop {

	public enum State {
		REQ_SENT, RESP_RECV
	}
	public enum Type {
		TIME_EXCEEDED, DESTINATION_UNREACHABLE
	}

	private TracerouteIpHop.State state;
	private TracerouteIpHop.Type type;
	private String ipAddr;
	private int ttl;
	private Date reqTimeStamp;
	private Date respTimeStamp;


	public TracerouteIpHop.State getState() {
		return state;
	}
	public void setState(TracerouteIpHop.State state) {
		this.state = state;
	}
	public TracerouteIpHop.Type getType() {
		return type;
	}
	public void setType(TracerouteIpHop.Type type) {
		this.type = type;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public int getTtl() {
		return ttl;
	}
	public void setTtl(int ttl) {
		this.ttl = ttl;
	}
	public Date getReqTimeStamp() {
		return reqTimeStamp;
	}
	public void setReqTimeStamp(Date reqTimeStamp) {
		this.reqTimeStamp = reqTimeStamp;
	}
	public Date getRespTimeStamp() {
		return respTimeStamp;
	}
	public void setRespTimeStamp(Date respTimeStamp) {
		this.respTimeStamp = respTimeStamp;
	}
	
	
	
	
}
