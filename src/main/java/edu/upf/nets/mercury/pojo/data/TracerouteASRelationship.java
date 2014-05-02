package edu.upf.nets.mercury.pojo.data;


public class TracerouteASRelationship {

	public enum Relationship{
		C2P, P2P, P2C, S2S, IXP, NF
	}
	
	private int as0;
	private int as1;
	private TracerouteASRelationship.Relationship relationship; 
	private int hop;

	public int getAs0() {
		return as0;
	}
	public void setAs0(int as0) {
		this.as0 = as0;
	}
	public int getAs1() {
		return as1;
	}
	public void setAs1(int as1) {
		this.as1 = as1;
	}
	public TracerouteASRelationship.Relationship getRelationship() {
		return relationship;
	}
	public void setRelationship(TracerouteASRelationship.Relationship relationship) {
		this.relationship = relationship;
	}
	public int getHop() {
		return hop;
	}
	public void setHop(int hop) {
		this.hop = hop;
	}


}
