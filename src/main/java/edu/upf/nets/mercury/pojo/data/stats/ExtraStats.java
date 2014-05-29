package edu.upf.nets.mercury.pojo.data.stats;


public class ExtraStats {

	private int srcAS;
	private String srcASName;
	private String srcIp;
	private String srcPublicIp;
	private String srcCity;
	private String srcCountry;
	
	private int dstAS;
	private String dstASName;
	private String dstIp;
	private String dst;
	private String dstCity;
	private String dstCountry;
	
	public ExtraStats(int srcAS, String srcASName, String srcIp,
			String srcPublicIp, String srcCity, String srcCountry, int dstAS,
			String dstASName, String dstIp, String dst, String dstCity,
			String dstCountry) {
		super();
		this.srcAS = srcAS;
		this.srcASName = srcASName;
		this.srcIp = srcIp;
		this.srcPublicIp = srcPublicIp;
		this.srcCity = srcCity;
		this.srcCountry = srcCountry;
		this.dstAS = dstAS;
		this.dstASName = dstASName;
		this.dstIp = dstIp;
		this.dst = dst;
		this.dstCity = dstCity;
		this.dstCountry = dstCountry;
	}
	
	public int getSrcAS() {
		return srcAS;
	}
	public void setSrcAS(int srcAS) {
		this.srcAS = srcAS;
	}
	public String getSrcASName() {
		return srcASName;
	}
	public void setSrcASName(String srcASName) {
		this.srcASName = srcASName;
	}
	public String getSrcIp() {
		return srcIp;
	}
	public void setSrcIp(String srcIp) {
		this.srcIp = srcIp;
	}
	public String getSrcPublicIp() {
		return srcPublicIp;
	}
	public void setSrcPublicIp(String srcPublicIp) {
		this.srcPublicIp = srcPublicIp;
	}
	public String getSrcCity() {
		return srcCity;
	}
	public void setSrcCity(String srcCity) {
		this.srcCity = srcCity;
	}
	public String getSrcCountry() {
		return srcCountry;
	}
	public void setSrcCountry(String srcCountry) {
		this.srcCountry = srcCountry;
	}
	public int getDstAS() {
		return dstAS;
	}
	public void setDstAS(int dstAS) {
		this.dstAS = dstAS;
	}
	public String getDstASName() {
		return dstASName;
	}
	public void setDstASName(String dstASName) {
		this.dstASName = dstASName;
	}
	public String getDstIp() {
		return dstIp;
	}
	public void setDstIp(String dstIp) {
		this.dstIp = dstIp;
	}
	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = dst;
	}
	public String getDstCity() {
		return dstCity;
	}
	public void setDstCity(String dstCity) {
		this.dstCity = dstCity;
	}
	public String getDstCountry() {
		return dstCountry;
	}
	public void setDstCountry(String dstCountry) {
		this.dstCountry = dstCountry;
	}
	
	
	
}
