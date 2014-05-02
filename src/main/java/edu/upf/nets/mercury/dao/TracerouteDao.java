package edu.upf.nets.mercury.dao;

import java.util.List;

import edu.upf.nets.mercury.pojo.data.TracerouteAS;
import edu.upf.nets.mercury.pojo.data.TracerouteIp;
import edu.upf.nets.mercury.pojo.data.TracerouteSettings;

public interface TracerouteDao {
	
	public String addTracerouteSettings(TracerouteSettings tracerouteSettings);
	
	public void removeTracerouteSettings(String tracerouteSettingsId);
	
	
	
	
	public void addTracerouteIp(TracerouteIp tracerouteIp);
	
	public void removeTracerouteIp(String tracerouteIpId);
	
	public List<TracerouteIp> getTracerouteIpsCustomQuery(String mongoQuery);
	
	
	
	
	
	public void addTracerouteAS(TracerouteAS tracerouteIpAS);
	
	public void addTracerouteASes(List<TracerouteAS> tracerouteIpASes);
	
	public void removeTracerouteAS(String tracerouteASId);
	
	public void dropTracerouteASCollection();
	
	public List<TracerouteAS> getTracerouteASes(int limit);
	
	public List<TracerouteAS> getTracerouteASesBySrcAS(int srcAS);
	
	public List<TracerouteAS> getTracerouteASesByDstAS(int dstAS);
	
	public List<TracerouteAS> getTracerouteASesByDst(String dst);
	
	public List<TracerouteAS> getTracerouteASesBySrcASAndDstAS(int srcAS, int dstAS);
	
	public List<TracerouteAS> getTracerouteASesBySrcASAndDst(int srcAS, String dst);
	
	public List<TracerouteAS> getTracerouteASesWithLteHops(int asHops);
	
	public List<TracerouteAS> getTracerouteASesWithLteHopsBySrcAS(int asHops, int srcAS);
	
	public List<TracerouteAS> getTracerouteASesWithLteHopsByDstAS(int asHops, int dstAS);

	public List<TracerouteAS> getTracerouteASesWithLteHopsByDst(int asHops, String dst);

	public List<TracerouteAS> getTracerouteASesCustomQuery(String mongoQuery);

}
