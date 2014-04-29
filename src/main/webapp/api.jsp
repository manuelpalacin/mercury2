<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>api</title>
	<%@ include file="head.jsp" %>
</head>


<body>

	<%@ include file="header.jsp" %>

	<div class="container bs-docs-container">

		<div class="row">
			<div class="col-md-9" role="main">
				<!-- API Overview -->
				<div class="bs-docs-section">
					<h1 id="overview" class="page-header">API Overview</h1>
					<p class="lead">Welcome to the Mercury API. This API is built using REST Web Service technologies. 
						Most of the methods can be invoked using simple HTTP-GET calls. 
						Although you can use your Internet browser for testing the API, we strongly recommend you to use <a href="http://curl.haxx.se/">cURL</a></p>
				</div>
				
				
				<!-- Get myInfo-->
				<div class="bs-docs-section">
					<h1 id="myinfo" class="page-header">Get my Info</h1>
				  	<h3 id="myinfo-desc">Description</h3>
					<p>Get my Info provides you information about your public IP address and the corresponding Autonomous System.</p>
					<h3 id="myinfo-req">Request</h3>
					<p><code>HTTP GET Request</code></p>
					<pre><code>http://mercury.upf.edu/mercury/api/services/myInfo</code></pre>
					<h3 id="myinfo-resp">Response</h3>
					<p><code>HTTP - 200OK JSON Response</code></p>
					<div class="highlight">
						<pre><code>
								{ "ip":"193.145.48.8", "as":766, "asName":"REDIRIS Entidad Publica Empresarial Red.es,ES", "timeStamp":"Mon Apr 28 11:23:12 CEST 2014" }
						</code></pre>
					</div>
				</div>
				
				
				<!-- Get AS Rel-->
				<div class="bs-docs-section">
					<h1 id="as-rel" class="page-header">Get AS Relationship</h1>
				  	<h3 id="as-rel-desc">Description</h3>
					<p>Get AS Relationship provides you information about the interconnection relationships between two adjacent Autonomous Systems. 
						AS relationships are extracted from <a href="http://as-rank.caida.org/">CAIDA AS Relationships project.</a> </p>
					<h4>Relationship types</h4>
					<ul>
						<li><code>-1</code> customer (c2p)</li>
						<li><code>0</code> peering (p2p)</li>
						<li><code>1</code> provider (p2c)</li>
						<li><code>2</code> sibling (s2s)</li>
						<li><code>10</code> not found</li>
					</ul>
					<h3 id="as-rel-req">Request</h3>
					<p><code>HTTP GET Request</code></p>
					<pre><code>http://mercury.upf.edu/mercury/api/services/getASRelationship/{as0}/{as1}</code></pre>
					<h4>Parameters</h4>
					<ul>
						<li><code>as0</code> is the AS number of the first Autonomous System</li>
						<li><code>as1</code> is the AS number of the second Autonomous System</li>
					</ul>
					<h3 id="as-rel-resp">Response</h3>
					<p><code>HTTP - 200OK JSON Response</code></p>
					<div class="highlight">
						<pre><code>
								{"as0":2,"as1":3,"relationship":-1}
						</code></pre>
					</div>
					
					<h3 id="as-rel-bulk-req">Bulk Request</h3>
					<p>A bulk request allows you to execute multiple queries in just one request. Limit: 1000 pairs</p>
					<p><code>HTTP POST Request</code></p>
					<pre><code>curl -X POST --data "pairs=2-3,766-3356,2589-6985" http://mercury.upf.edu/mercury/api/services/getASRelationshipsPOST</code></pre>
					<h3 id="as-rel-bulk-resp">Bulk Response</h3>
					<p><code>HTTP - 200OK JSON Response</code></p>
					<div class="highlight">
						<pre><code>
								[{"id":null,"as0":2,"as1":3,"relationship":-1},{"id":null,"as0":766,"as1":3356,"relationship":1},{"id":null,"as0":10,"as1":11,"relationship":10}]
						</code></pre>
					</div>
				</div>
				

				<!-- Get IP 2 ASN mapping -->
				<div class="bs-docs-section">
					<h1 id="ip2asn" class="page-header">Get IP to ASN Mapping</h1>
				  	<h3 id="ip2asn-desc">Description</h3>
					<p>Get IP2ASN Mapping provides you information about the corresponding AS number for an IP address. 
						IP to ASN translations are inferred from analyzing BGP messages from <a href="http://thyme.apnic.net/">BGP Routing Table Analysis.</a> </p>
					<h3 id="ip2asn-req">Request</h3>
					<p><code>HTTP GET Request</code></p>
					<pre><code>http://mercury.upf.edu/mercury/api/services/getIp2AsnMappingsByIps/{ips}</code></pre>
					<h4>Parameters</h4>
					<ul>
						<li><code>ips</code> comma-separated IP addresses. E.g. .../193.145.48.3,8.8.8.8</li>
					</ul>
					<h3 id="ip2asn-resp">Response</h3>
					<p><code>HTTP - 200OK JSON Response</code></p>
					<div class="highlight">
						<pre><code>
								[[{"as":766,"asName":"REDIRIS Entidad Publica Empresarial Red.es,ES","ip":"193.145.48.3","rangeHigh":3247702015,"ixpParticipantName":null,"numIps":262144,"rangeLow":3247439872,"ixpParticipant":0,"timeStamp":1398612049556,"prefix":"193.144.0.0/14","location":null,"id":"535d205ee4b043743e72a38d","type":"AS"}],[{"as":15169,"asName":"GOOGLE - Google Inc.,US","ip":"8.8.8.8","rangeHigh":134744319,"ixpParticipantName":null,"numIps":256,"rangeLow":134744064,"ixpParticipant":0,"timeStamp":1398612048467,"prefix":"8.8.8.0/24","location":null,"id":"535d205ee4b043743e6d7303","type":"AS"},{"as":3356,"asName":"LEVEL3 - Level 3 Communications, Inc.,US","ip":"8.8.8.8","rangeHigh":142606335,"ixpParticipantName":null,"numIps":8388608,"rangeLow":134217728,"ixpParticipant":0,"timeStamp":1398612048466,"prefix":"8.0.0.0/9","location":null,"id":"535d205ee4b043743e6d72ad","type":"AS"},{"as":3356,"asName":"LEVEL3 - Level 3 Communications, Inc.,US","ip":"8.8.8.8","rangeHigh":150994943,"ixpParticipantName":null,"numIps":16777216,"rangeLow":134217728,"ixpParticipant":0,"timeStamp":1398612048466,"prefix":"8.0.0.0/8","location":null,"id":"535d205ee4b043743e6d72ac","type":"AS"}]]
						</code></pre>
					</div>
					
					<h3 id="ip2asn-bulk-req">Bulk Request</h3>
					<p>A bulk request allows you to execute multiple queries in just one request. Limit: 1000 IP addresses</p>
					<p><code>HTTP POST Request</code></p>
					<pre><code>curl -X POST --data "ips=193.145.48.3,8.8.8.85" http://mercury.upf.edu/mercury/api/services/getIp2AsnMappingsByIpsPOST</code></pre>
					<h3 id="ip2asn-bulk-resp">Bulk Response</h3>
					<p><code>HTTP - 200OK JSON Response</code></p>
					<div class="highlight">
						<pre><code>
								[[{"as":766,"asName":"REDIRIS Entidad Publica Empresarial Red.es,ES","ip":"193.145.48.3","rangeHigh":3247702015,"ixpParticipantName":null,"numIps":262144,"rangeLow":3247439872,"ixpParticipant":0,"timeStamp":1398612049556,"prefix":"193.144.0.0/14","location":null,"id":"535d205ee4b043743e72a38d","type":"AS"}],[{"as":15169,"asName":"GOOGLE - Google Inc.,US","ip":"8.8.8.8","rangeHigh":134744319,"ixpParticipantName":null,"numIps":256,"rangeLow":134744064,"ixpParticipant":0,"timeStamp":1398612048467,"prefix":"8.8.8.0/24","location":null,"id":"535d205ee4b043743e6d7303","type":"AS"},{"as":3356,"asName":"LEVEL3 - Level 3 Communications, Inc.,US","ip":"8.8.8.8","rangeHigh":142606335,"ixpParticipantName":null,"numIps":8388608,"rangeLow":134217728,"ixpParticipant":0,"timeStamp":1398612048466,"prefix":"8.0.0.0/9","location":null,"id":"535d205ee4b043743e6d72ad","type":"AS"},{"as":3356,"asName":"LEVEL3 - Level 3 Communications, Inc.,US","ip":"8.8.8.8","rangeHigh":150994943,"ixpParticipantName":null,"numIps":16777216,"rangeLow":134217728,"ixpParticipant":0,"timeStamp":1398612048466,"prefix":"8.0.0.0/8","location":null,"id":"535d205ee4b043743e6d72ac","type":"AS"}]]
						</code></pre>
					</div>
				</div>

				<!-- Get IP 2 GEO -->
				<div class="bs-docs-section">
					<h1 id="ip2geo" class="page-header">Get IP to Geo Mapping</h1>
				  	<h3 id="ip2geo-desc">Description</h3>
					<p>Get IP2GEO Mapping provides you information about the geolocation for an IP address. 
						IP to GEO translations are extracted from <a href="http://dev.maxmind.com/geoip/legacy/geolite/">MaxMind GeoLite database.</a> </p>
					<h3 id="ip2geo-req">Request</h3>
					<p><code>HTTP GET Request</code></p>
					<pre><code>http://mercury.upf.edu/mercury/api/services/getIps2Geo/{ips}</code></pre>
					<h4>Parameters</h4>
					<ul>
						<li><code>ips</code> comma-separated IP addresses. E.g. .../193.145.48.3,8.8.8.8</li>
					</ul>
					<h3 id="ip2geo-resp">Response</h3>
					<p><code>HTTP - 200OK JSON Response</code></p>
					<div class="highlight">
						<pre><code>
						[{"countryCode":"ES","countryName":"Spain","region":null,"city":null,"postalCode":null,"latitude":40.0,"longitude":-4.0,"dma_code":0,"area_code":0,"metro_code":0,"ip":"193.145.5.4"},{"countryCode":"US","countryName":"United States","region":null,"city":null,"postalCode":null,"latitude":38.0,"longitude":-97.0,"dma_code":0,"area_code":0,"metro_code":0,"ip":"8.8.8.8"}]
						</code></pre>
					</div>
					
					<h3 id="ip2geo-bulk-req">Bulk Request</h3>
					<p>A bulk request allows you to execute multiple queries in just one request. Limit: 1000 IP addresses</p>
					<p><code>HTTP POST Request</code></p>
					<pre><code>curl -X POST --data "ips=193.145.48.3,8.8.8.85" http://mercury.upf.edu/mercury/api/services/getIps2GeoPOST</code></pre>
					<h3 id="ip2geo-bulk-resp">Bulk Response</h3>
					<p><code>HTTP - 200OK JSON Response</code></p>
					<div class="highlight">
						<pre><code>
						[{"countryCode":"ES","countryName":"Spain","region":null,"city":null,"postalCode":null,"latitude":40.0,"longitude":-4.0,"dma_code":0,"area_code":0,"metro_code":0,"ip":"193.145.5.4"},{"countryCode":"US","countryName":"United States","region":null,"city":null,"postalCode":null,"latitude":38.0,"longitude":-97.0,"dma_code":0,"area_code":0,"metro_code":0,"ip":"8.8.8.8"}]
						</code></pre>
					</div>
				</div>
				
				<!-- Get IP 2 ANS and GEO -->
				<div class="bs-docs-section">
					<h1 id="ip2asngeo" class="page-header">Get IP to AS and Geo Mapping</h1>
				  	<h3 id="ip2asngeo-desc">Description</h3>
					<p>Get IP2ASNandGEO Mapping provides you information about the AS and the geolocation for an IP address. 
						This method combines the previous two methods </p>
					<h3 id="ip2asngeo-req">Request</h3>
					<p><code>HTTP GET Request</code></p>
					<pre><code>http://mercury.upf.edu/mercury/api/services/getIps2AsnGeo/{ips}</code></pre>
					<h4>Parameters</h4>
					<ul>
						<li><code>ips</code> comma-separated IP addresses. E.g. .../193.145.48.3,8.8.8.8</li>
					</ul>
					<h3 id="ip2asngeo-resp">Response</h3>
					<p><code>HTTP - 200OK JSON Response</code></p>
					<div class="highlight">
						<pre><code>
							[[{"as":766,"asName":"REDIRIS Entidad Publica Empresarial Red.es,ES","ip":"193.145.5.4","rangeHigh":3247702015,"ixpParticipantName":null,"numIps":262144,"rangeLow":3247439872,"ixpParticipant":0,"timeStamp":1398612049556,"prefix":"193.144.0.0/14","location":{"countryCode":"ES","countryName":"Spain","region":null,"city":null,"postalCode":null,"latitude":40.0,"longitude":-4.0,"dma_code":0,"area_code":0,"metro_code":0,"ip":"193.145.5.4"},"id":"535d205ee4b043743e72a38d","type":"AS"}],[{"as":15169,"asName":"GOOGLE - Google Inc.,US","ip":"8.8.8.8","rangeHigh":134744319,"ixpParticipantName":null,"numIps":256,"rangeLow":134744064,"ixpParticipant":0,"timeStamp":1398612048467,"prefix":"8.8.8.0/24","location":{"countryCode":"US","countryName":"United States","region":null,"city":null,"postalCode":null,"latitude":38.0,"longitude":-97.0,"dma_code":0,"area_code":0,"metro_code":0,"ip":"8.8.8.8"},"id":"535d205ee4b043743e6d7303","type":"AS"},{"as":3356,"asName":"LEVEL3 - Level 3 Communications, Inc.,US","ip":"8.8.8.8","rangeHigh":142606335,"ixpParticipantName":null,"numIps":8388608,"rangeLow":134217728,"ixpParticipant":0,"timeStamp":1398612048466,"prefix":"8.0.0.0/9","location":{"countryCode":"US","countryName":"United States","region":null,"city":null,"postalCode":null,"latitude":38.0,"longitude":-97.0,"dma_code":0,"area_code":0,"metro_code":0,"ip":"8.8.8.8"},"id":"535d205ee4b043743e6d72ad","type":"AS"},{"as":3356,"asName":"LEVEL3 - Level 3 Communications, Inc.,US","ip":"8.8.8.8","rangeHigh":150994943,"ixpParticipantName":null,"numIps":16777216,"rangeLow":134217728,"ixpParticipant":0,"timeStamp":1398612048466,"prefix":"8.0.0.0/8","location":{"countryCode":"US","countryName":"United States","region":null,"city":null,"postalCode":null,"latitude":38.0,"longitude":-97.0,"dma_code":0,"area_code":0,"metro_code":0,"ip":"8.8.8.8"},"id":"535d205ee4b043743e6d72ac","type":"AS"}]]
						</code></pre>
					</div>
					
					<h3 id="ip2asngeo-bulk-req">Bulk Request</h3>
					<p>A bulk request allows you to execute multiple queries in just one request. Limit: 1000 IP addresses</p>
					<p><code>HTTP POST Request</code></p>
					<pre><code>curl -X POST --data "ips=193.145.48.3,8.8.8.85" http://mercury.upf.edu/mercury/api/services/getIps2AsnGeoPOST</code></pre>
					<h3 id="ip2asngeo-bulk-resp">Bulk Response</h3>
					<p><code>HTTP - 200OK JSON Response</code></p>
					<div class="highlight">
						<pre><code>
							[[{"as":766,"asName":"REDIRIS Entidad Publica Empresarial Red.es,ES","ip":"193.145.5.4","rangeHigh":3247702015,"ixpParticipantName":null,"numIps":262144,"rangeLow":3247439872,"ixpParticipant":0,"timeStamp":1398612049556,"prefix":"193.144.0.0/14","location":{"countryCode":"ES","countryName":"Spain","region":null,"city":null,"postalCode":null,"latitude":40.0,"longitude":-4.0,"dma_code":0,"area_code":0,"metro_code":0,"ip":"193.145.5.4"},"id":"535d205ee4b043743e72a38d","type":"AS"}],[{"as":15169,"asName":"GOOGLE - Google Inc.,US","ip":"8.8.8.8","rangeHigh":134744319,"ixpParticipantName":null,"numIps":256,"rangeLow":134744064,"ixpParticipant":0,"timeStamp":1398612048467,"prefix":"8.8.8.0/24","location":{"countryCode":"US","countryName":"United States","region":null,"city":null,"postalCode":null,"latitude":38.0,"longitude":-97.0,"dma_code":0,"area_code":0,"metro_code":0,"ip":"8.8.8.8"},"id":"535d205ee4b043743e6d7303","type":"AS"},{"as":3356,"asName":"LEVEL3 - Level 3 Communications, Inc.,US","ip":"8.8.8.8","rangeHigh":142606335,"ixpParticipantName":null,"numIps":8388608,"rangeLow":134217728,"ixpParticipant":0,"timeStamp":1398612048466,"prefix":"8.0.0.0/9","location":{"countryCode":"US","countryName":"United States","region":null,"city":null,"postalCode":null,"latitude":38.0,"longitude":-97.0,"dma_code":0,"area_code":0,"metro_code":0,"ip":"8.8.8.8"},"id":"535d205ee4b043743e6d72ad","type":"AS"},{"as":3356,"asName":"LEVEL3 - Level 3 Communications, Inc.,US","ip":"8.8.8.8","rangeHigh":150994943,"ixpParticipantName":null,"numIps":16777216,"rangeLow":134217728,"ixpParticipant":0,"timeStamp":1398612048466,"prefix":"8.0.0.0/8","location":{"countryCode":"US","countryName":"United States","region":null,"city":null,"postalCode":null,"latitude":38.0,"longitude":-97.0,"dma_code":0,"area_code":0,"metro_code":0,"ip":"8.8.8.8"},"id":"535d205ee4b043743e6d72ac","type":"AS"}]]
						</code></pre>
					</div>
				</div>	
								
				
			</div><!-- End col main -->
			
			
			<div class="col-md-3">
				<div class="bs-docs-sidebar hidden-print" role="complementary">
	            	<ul class="nav bs-docs-sidenav">
	                	<li><a href="#overview">Overview</a></li>
						<li><a href="#myinfo">My info</a></li>
						<li><a href="#as-rel">Get AS Relationship</a></li>
						<li><a href="#ip2asn">Get IP to ASN Mapping</a></li>
						<li><a href="#ip2geo">Get IP to Geo Mapping</a></li>
						<li><a href="#ip2asngeo">Get IP to AS and Geo Mapping</a></li>
					</ul>
		            <a class="back-to-top" href="#top">
		              Back to top
		            </a>

		        </div>
			</div><!-- col menu -->
			
		</div><!-- row  -->
	</div><!-- container -->

	<%@ include file="footer.jsp" %>
	
	
</body>
</html>