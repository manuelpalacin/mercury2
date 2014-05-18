package edu.upf.nets.mercury.action;


public interface TracerouteAction {
	

	public String getLastTraceroutes();
	
	public String getTraceroute();
	
	public String getASTracerouteStatsByDestination();
	
	public String getASTracerouteStatsByDestinationAS();

	public String getASTracerouteStatsByOriginAS();
	
	public String getASTracerouteStatsByDestinationCity();
	
	public String getASTracerouteStatsByDestinationCountry();
	
	public String getASTracerouteStatsByOriginCity();
	
	public String getASTracerouteStatsByOriginCountry();
	
	
	
}
