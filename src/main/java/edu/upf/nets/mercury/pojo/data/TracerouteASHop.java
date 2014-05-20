package edu.upf.nets.mercury.pojo.data;


public class TracerouteASHop {
	

	public enum Type{
		AS, IXP
	}
	
	private int hop;
	private int as;
	private String asName;
	private String ixpName;
	private TracerouteASHop.Type type;
	//private boolean inferred;//We use an heuristic to determine what is the most suitable AS
	
	
	public int getHop() {
		return hop;
	}
	public void setHop(int hop) {
		this.hop = hop;
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
	public String getIxpName() {
		return ixpName;
	}
	public void setIxpName(String ixpName) {
		this.ixpName = ixpName;
	}
	public TracerouteASHop.Type getType() {
		return type;
	}
	public void setType(TracerouteASHop.Type type) {
		this.type = type;
	}
//	public boolean isInferred() {
//		return inferred;
//	}
//	public void setInferred(boolean inferred) {
//		this.inferred = inferred;
//	}
	
	
	
	

}
