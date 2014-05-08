package edu.upf.nets.mercury.pojo.data;

import java.util.ArrayList;
import java.util.List;


public class TracerouteASStats {
	
	private int asHops;
	private int c2pRels;
	private int p2pRels;
	private int p2cRels;
	private int s2sRels;
	private int ixpRels;
	private int nfRels;
	private boolean completed;
	private int flags;
	private List<TracerouteASRelationship> tracerouteASRelationships;
	
	public TracerouteASStats(){}
	
	public TracerouteASStats(int asHops, int c2pRels, int p2pRels, int p2cRels,
			int s2sRels, int ixpRels, int nfRels, boolean completed,
			int flags) {
		super();
		this.asHops = asHops;
		this.c2pRels = c2pRels;
		this.p2pRels = p2pRels;
		this.p2cRels = p2cRels;
		this.s2sRels = s2sRels;
		this.ixpRels = ixpRels;
		this.nfRels = nfRels;
		this.completed = completed;
		this.flags = flags;
	}
	
	public int getAsHops() {
		return asHops;
	}
	public void setAsHops(int asHops) {
		this.asHops = asHops;
	}
	public int getC2pRels() {
		return c2pRels;
	}
	public void setC2pRels(int c2pRels) {
		this.c2pRels = c2pRels;
	}
	public int getP2pRels() {
		return p2pRels;
	}
	public void setP2pRels(int p2pRels) {
		this.p2pRels = p2pRels;
	}
	public int getP2cRels() {
		return p2cRels;
	}
	public void setP2cRels(int p2cRels) {
		this.p2cRels = p2cRels;
	}
	public int getS2sRels() {
		return s2sRels;
	}
	public void setS2sRels(int s2sRels) {
		this.s2sRels = s2sRels;
	}
	public int getIxpRels() {
		return ixpRels;
	}
	public void setIxpRels(int ixpRels) {
		this.ixpRels = ixpRels;
	}
	public int getNfRels() {
		return nfRels;
	}
	public void setNfRels(int nfRels) {
		this.nfRels = nfRels;
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
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	
	

}
