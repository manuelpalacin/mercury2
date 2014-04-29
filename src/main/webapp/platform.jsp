<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>platform</title>
	<%@ include file="head.jsp" %>
</head>


<body>

	<%@ include file="header.jsp" %>

	<div class="container bs-docs-container">
		<div class="row">
			<div class="col-md-9" role="main">
				
				<div class="bs-docs-section">
					<h1  class="page-header">Platform information</h1>
					<p>The main objective of the Mercury Platform is to identify the interconnection strategies between the content providers and the access ISPs. 
					 We aim to discover the path that Internet traffic follows across the different networks most and to reveal the hidden interconnections between different ISPs.
					 To this end, we collect <code>traceroute</code> information from different participants located in different ISPs. The participants execute a client that probes a list
					 of the most popular web sites for the region of execution and processes each IP hop to determine the AS path to reach each destination. Once the trace paths are processed,
					 the results are sent to the Mercury database where we store them. Finally, users can extract and manipulate the obtained data using the web interface or the REST API.     
					</p>
				</div>
				
				
				<div class="bs-docs-section">
					<h1  class="page-header">Platform components</h1>
					<p>The Mercury Platform is composed by different elements and data sources. 
					Basically the Platform is formed by the Desktop clients executed by the participants that proves the web destinations from different geographic locations and the Web Platform that receives, stores and visualizes the results from the clients. 
					</p>
					<ul>
						<li>The Mercury Desktop Client</li>
						<li>The Mercury Web Platform</li>
						<ul>	
							<li>Web interface</li>
							<li>REST-API</li>
							<li>Database</li>
							<li>Filters</li>
						</ul>
						<li>External data sources</li>
						<ul>
							<li>BGP Routing Table Analysis</li>
							<li>Peering DB</li>
							<li>Alexa Top Sites</li>
							<li>CAIDA AS Relationships</li>
							<li>MaxMind GeoLite</li>
						</ul>	
					</ul>
				</div>
								
				<div class="bs-docs-section">
					<h1  class="page-header">Methodology</h1>
					<p>   
					The Mercury Web Platform is a complex system that aggregates updated information about:
					</p>
					<ul> 
						<li>Mappings between IP prefixes and the Autonomous System from which they belong. This information is extracted and processed from 
						<a href="http://thyme.apnic.net/">BGP Routing Table Analysis</a> which obtains BGP information 
						from different monitors of <a href="http://www.routeviews.org/">RouteViews</a> and <a href="https://www.apnic.net/">APNIC</a>.</li>
						
						<li>AS interconnection relationships between hundreds of Autonomous Systems. This information is extracted from the <a href="http://as-rank.caida.org/">AS Relationship</a> database from CAIDA. 
						Despite being a complete data set, it misses some critical interconnection agreements due to they are hard to detect (e.g. direct interconnection agreements through private paid-peering between some ISPs). 
						The detection of this hidden interconnection is one of the main motivations of this project.</li>
						
						<li>Interconnection eXchange Points information provided by the <a href="https://www.peeringdb.com">Peering DB</a> database. 
						It provides information about the IP prefixes that the ISP participants use in the different IXPs.</li>
						
						<li>Mappings between IP addresses and their geographic position. This information is provided by <a href="dev.maxmind.com/geoip/geolite">MaxMind</a>. 
						We have noticed that sometimes this information is inaccurate (e.g.  IP addresses from Google are pointed to Mountain View, CA, USA). </li>
						
						<li>URL destinations from the most popular web sites. This information is extracted from <a href="www.alexa.com/topsites/">Alexa Top Sites</a>. 
						Once we have the Top 100 from more than 20 countries we extract the embedded URLs of each site that make us to obtain near 800 URLs to probe for each country</li>
						
					</ul>
			 
					<p>  All this information is stored in a MongoDB data storage in combination with the measurements from the Desktop clients. 
					Users can visualize all the data using the Web interface and can apply filters to select the required information.
					The Web Platform also offers a REST-API that facilitates the automation of the query process .
					</p>
					
					<p> The Desktop clients are executed by project participants that download the application. The clients are executed in different ISP networks assuring the heterogeneity of the data-sources. The data gathering consist in the following process:</p>
					<ol>
						<li>The client downloads a list of URLs from the Web Platform. We use the Operating System locale to determine the country of the participant before sending the list. 
						The URL list is composed by main domains from the most popular web sites and external links embedded in these pages.</li>
						<li>The client executes an <code>nslookup</code> to obtain the IP(s) address(es) for each URL.</li>
						<li>The client executes a <code>traceroute</code> for each IP address to determine the IP path.
						 We have implemented <code>Paris traceroute</code> instead of using classic <code>Van Jacobson traceroute</code> because it minimizes the problem of the multi-path nature of the IP routing and we take advantage of the per-flow load balancing that implements lots of routers. 
						 We implement <code>Paris traceroute</code> modifying some fields of the IP header and the ICMP or UDP header and we send 5 attemps for each one of the 5 flows that we generate. Finally we sanitize the obtained result and discard the invalid traces 
						 (e.g. too many destination unreachable messages, packet dropping, impossible to avoid multi-path). </li>
						<li>The client translates each IP from each IP hop to its corresponding AS number using the Web Platform. The client tries to repair inconsistencies such as AS loops.</li>
						<li>The client geolocates each IP using the Web Platform.</li>
						<li>The client determines the AS relationships between the ASes of a path using the Web Platform.</li>
						<li>The client analyzes each trace and mark them with different colors (green, yellow, red) and additional information depending on how complete the traces are. </li>
						<li>Finally, the client uploads all the pre-processed measurements to the Web Platform using the REST-API.</li>
					</ol>
					
					<p>Aggregating measurements from multiple locations we can create AS topologies that will help us to better understand how the ISPs are interconnected.

					</p>
					<div class="thumbnail">
				   		<img src="img/traceroute.png" alt="traceroute" class="img-rounded">
				   	</div>
					<div class="thumbnail">
				   		<img src="img/topology.png" alt="topology" class="img-rounded">
				   	</div>
				</div>
				
				
				
				
				
			</div>
		</div>
	</div>
	<!-- /container -->
	
	<%@ include file="footer.jsp" %>
	
	
</body>
</html>