package edu.upf.nets.mercury.pojo.data;

import java.util.ArrayList;
import java.util.List;


public class TracerouteIpAttempt {
	
	public enum State {
		COMPLETED, UNREACHABLE
	}
	

	private List<TracerouteIpHop> tracerouteIpHops;
	private TracerouteIpAttempt.State state;
	private int maxTTL;
	private String tracerouteIpAttemptId;
	

	public List<TracerouteIpHop> getTracerouteIpHops() {
		if(tracerouteIpHops == null){
			tracerouteIpHops = new ArrayList<TracerouteIpHop>();
		}
		return tracerouteIpHops;
	}
	public void setTracerouteIpHops(List<TracerouteIpHop> tracerouteIpHops) {
		this.tracerouteIpHops = tracerouteIpHops;
	}
	public void addTracerouteIpHop(TracerouteIpHop tracerouteIpHop) {
		getTracerouteIpHops().add(tracerouteIpHop);
	}
	public TracerouteIpAttempt.State getState() {
		return state;
	}
	public void setState(TracerouteIpAttempt.State state) {
		this.state = state;
	}
	public int getMaxTTL() {
		return maxTTL;
	}
	public void setMaxTTL(int maxTTL) {
		this.maxTTL = maxTTL;
	}
	public String getTracerouteIpAttemptId() {
		return tracerouteIpAttemptId;
	}
	public void setTracerouteIpAttemptId(String tracerouteIpAttemptId) {
		this.tracerouteIpAttemptId = tracerouteIpAttemptId;
	}
	
	

}
