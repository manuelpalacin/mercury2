package edu.upf.nets.mercury.pojo.data;

import java.util.ArrayList;
import java.util.List;

public class TracerouteIpFlow {
	
	
	public enum Algorithm {
		ICMP, UDP_IDENTIFICATION, UDP_CHECKSUM, UDP_BOTH
	}
	

	private List<TracerouteIpAttempt> tracerouteIpAttemps;
	private TracerouteIpFlow.Algorithm algorithm;
	private String flowId;
	

	public List<TracerouteIpAttempt> getTracerouteIpAttemps() {
		if(this.tracerouteIpAttemps == null){
			tracerouteIpAttemps = new ArrayList<TracerouteIpAttempt>();
		}
		return tracerouteIpAttemps;
	}
	public void setTracerouteIpAttemps(List<TracerouteIpAttempt> tracerouteIpAttemps) {
		this.tracerouteIpAttemps = tracerouteIpAttemps;
	}
	public void addTracerouteIpAttemp(TracerouteIpAttempt tracerouteIpAttemp){
		getTracerouteIpAttemps().add(tracerouteIpAttemp);
	}
	public TracerouteIpFlow.Algorithm getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(TracerouteIpFlow.Algorithm algorithm) {
		this.algorithm = algorithm;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	
	
	
	

}
