package edu.upf.nets.mercury.pojo.data;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "traceroutesettings")
public class TracerouteSettings {
	
	
	@Id
	private String id;
	private int attemptsPerFlow;
	private int flowCount;
	private int minHops;
	private int maxHops;
	private int attemptDelay;
	private int hopTimeout;
	private int minPort;
	private int maxPort;
	private int dataLength;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAttemptsPerFlow() {
		return attemptsPerFlow;
	}
	public void setAttemptsPerFlow(int attemptsPerFlow) {
		this.attemptsPerFlow = attemptsPerFlow;
	}
	public int getFlowCount() {
		return flowCount;
	}
	public void setFlowCount(int flowCount) {
		this.flowCount = flowCount;
	}
	public int getMinHops() {
		return minHops;
	}
	public void setMinHops(int minHops) {
		this.minHops = minHops;
	}
	public int getMaxHops() {
		return maxHops;
	}
	public void setMaxHops(int maxHops) {
		this.maxHops = maxHops;
	}
	public int getAttemptDelay() {
		return attemptDelay;
	}
	public void setAttemptDelay(int attemptDelay) {
		this.attemptDelay = attemptDelay;
	}
	public int getHopTimeout() {
		return hopTimeout;
	}
	public void setHopTimeout(int hopTimeout) {
		this.hopTimeout = hopTimeout;
	}
	public int getMinPort() {
		return minPort;
	}
	public void setMinPort(int minPort) {
		this.minPort = minPort;
	}
	public int getMaxPort() {
		return maxPort;
	}
	public void setMaxPort(int maxPort) {
		this.maxPort = maxPort;
	}
	public int getDataLength() {
		return dataLength;
	}
	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}
	
	
	
	

}
