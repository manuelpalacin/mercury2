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
	public void addRelationship(Integer rel) {
        switch (rel) {
            case -1:  this.relationship = Relationship.P2C;
                     break;
            case 0:  this.relationship = Relationship.P2P;
                     break;
            case 1:  this.relationship = Relationship.C2P;
                     break;
            case 2:  this.relationship = Relationship.S2S;
                     break;
            case 3:  this.relationship = Relationship.IXP;
                     break;
            case 10:  this.relationship = Relationship.NF;
                     break;
            default: this.relationship = Relationship.NF;
                     break;
        }
		
	}
	
	public int getHop() {
		return hop;
	}
	public void setHop(int hop) {
		this.hop = hop;
	}


}
