<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>resources</title>
	<%@ include file="head.jsp" %>
</head>


<body>

	<%@ include file="header.jsp" %>

	<div class="container">
		<h1 id="resources" class="page-header">Resources</h1>
					<p class="lead">The Mercury Platform uses resources from multiple projects. 
					Here we show most of the resources and technologies we use in Mercury. 
						</p>
		<div class="row">
		
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		   	  <img src="img/caida-logo.png" alt="caida-logo" class="img-rounded">
		      <div class="caption">
		        <h3>CAIDA AS Relationships</h3>
		        <p>The CAIDA AS Relationships project provides us information about the interconnection relationship between two Autonomous Systems.</p>
		        <p><a href="http://as-rank.caida.org/" class="btn btn-primary" role="button">Visit</a> </p>
		      </div>
		    </div>
		  </div>
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		      <img src="img/maxmind-logo.png" alt="caida-logo" class="img-rounded">
		      <div class="caption">
		        <h3>MaxMind GeoLite</h3>
		        <p>MaxMind provides us geographic information for an specific IP address.</p>
		        <p><a href="http://dev.maxmind.com/geoip/legacy/geolite/" class="btn btn-primary" role="button">Visit</a> </p>
		      </div>
		    </div>
		  </div>
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		      <img src="img/thyme-logo.png" alt="thyme-logo" class="img-rounded">
		      <div class="caption">
		        <h3>BGP Routing Table Analysis</h3>
		        <p>The BGP Routing Table Analysis allows us to associate IP addresses with their Autonomous Systems. 
		        We update Mercury using the daily reports.</p>
		        <p><a href="http://thyme.apnic.net/about.html" class="btn btn-primary" role="button">Visit</a> </p>
		      </div>
		    </div>
		  </div>		  		  
		</div><!-- end row1 -->
		
		
		<div class="row">
		
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		   	  <img src="img/alexa-logo.png" alt="alexa-logo" class="img-rounded">
		      <div class="caption">
		        <h3>Alexa Top Sites</h3>
		        <p>The Alexa Top Sites provides us information about the most popular web sites for each country.</p>
		        <p><a href="http://www.alexa.com/topsites" class="btn btn-primary" role="button">Visit</a> </p>
		      </div>
		    </div>
		  </div>
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		      <img src="img/cdnplanet-logo.png" alt="cdnplanet-logo" class="img-rounded">
		      <div class="caption">
		        <h3>CDN Finder</h3>
		        <p>We have modified the source code of CDN Finder for extracting all the URLs from the most popular web sites.</p>
		        <p><a href="https://github.com/sajal/cdnfinder.js" class="btn btn-primary" role="button">Visit</a> </p>
		      </div>
		    </div>
		  </div>
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		      <img src="img/paristr-logo.png" alt="paristr-logo" class="img-rounded">
		      <div class="caption">
		        <h3>Paris traceroute</h3>
		        <p>We have implemented the Paris Traceroute algorithm for minimizing the multi-path effect of the IP routing nature.</p>
		        <p><a href="http://www.paris-traceroute.net/" class="btn btn-primary" role="button">Visit</a> </p>
		      </div>
		    </div>
		  </div>		  		  
		</div><!-- end row2 -->
		
		<div class="row">
		
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		   	  <img src="img/peeringdb-logo.png" alt="peeringdb-logo" class="img-rounded">
		      <div class="caption">
		        <h3>Peering DB</h3>
		        <p>The Peering DB provides us information about peer participants in the Internet eXchange Points around the globe.</p>
		        <p><a href="https://www.peeringdb.com" class="btn btn-primary" role="button">Visit</a> </p>
		      </div>
		    </div>
		  </div>
		  		  
		</div><!-- end row3 -->		
		
	</div>
	<!-- /container -->
	
	<%@ include file="footer.jsp" %>
	
	
</body>
</html>