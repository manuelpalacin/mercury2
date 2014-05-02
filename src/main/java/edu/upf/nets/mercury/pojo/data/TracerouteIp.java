package edu.upf.nets.mercury.pojo.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tracerouteip")
public class TracerouteIp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private String id;
	private String srcIp;
	private String srcPublicIp;
	private String dstIp;
	private String dst;
	private List<TracerouteIpFlow> tracerouteIpFlows;
	private String tracerouteSettingsId;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public List<TracerouteIpFlow> getTracerouteIpFlows() {
		if(this.tracerouteIpFlows == null){
			tracerouteIpFlows = new ArrayList<TracerouteIpFlow>();
		}
		return tracerouteIpFlows;
	}
	public void setTracerouteFlows(List<TracerouteIpFlow> tracerouteIpFlows) {
		this.tracerouteIpFlows = tracerouteIpFlows;
	}
	public void addTracerouteIpFlows(TracerouteIpFlow tracerouteIpFlow){
		getTracerouteIpFlows().add(tracerouteIpFlow);
	}
	public String getTracerouteSettingsId() {
		return tracerouteSettingsId;
	}
	public void setTracerouteSettingsId(String tracerouteSettingsId) {
		this.tracerouteSettingsId = tracerouteSettingsId;
	}
	
	
	
	

}
