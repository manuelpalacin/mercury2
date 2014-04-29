package edu.upf.nets.mercury.pojo.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TracerouteAttemp {
	
	public enum State {
		COMPLETED, UNREACHABLE
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private String id;
	private List<TracerouteIpHop> tracerouteIpHops;
	private TracerouteAttemp.State state;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public TracerouteAttemp.State getState() {
		return state;
	}
	public void setState(TracerouteAttemp.State state) {
		this.state = state;
	}
	
	

}
