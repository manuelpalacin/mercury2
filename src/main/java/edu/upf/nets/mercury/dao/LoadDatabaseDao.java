package edu.upf.nets.mercury.dao;

import java.util.List;

import edu.upf.nets.mercury.pojo.ASInfo;
import edu.upf.nets.mercury.pojo.Ip2ASMapping;

public interface LoadDatabaseDao {
	
	public void loadASRelationships();
	
	public Integer getASRelationship(int as0, int as1);
	
	public void dropIp2AsnMappings();
	
	public void dropIp2AsnMappingsNoIxps();
	
	public void addIp2AsnMapping(Ip2ASMapping ip2ASMapping);
	
	public void addIp2AsnMappings(List<Ip2ASMapping> list);
	
	public void removeIp2AsnMapping(int as, String prefix);
	
	public void removeIp2AsnMapping(Ip2ASMapping ip2ASMapping);
	
	public Ip2ASMapping getIp2AsnMappingByIp(long ip);
	
	public List<Ip2ASMapping> getIp2AsnMappingsByIp(long ip);
	
	public Ip2ASMapping existIp2AsnMapping(int as, String prefix);
	
	public List<Ip2ASMapping> detectMappingsInRange(long rangeLow, long rangeHigh);
	
	public void dropASInfos();
	
	public void addASInfo(ASInfo asInfo);
	
	public String getASName(int as);

}
