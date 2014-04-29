<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>about</title>
	<%@ include file="head.jsp" %>
</head>


<body>

	<%@ include file="header.jsp" %>

	<div class="container">
		<h1>About</h1>
		<p>The Mercury Platform was born in 2013 at the <a href="http://www.nets.upf.edu">NeTS Research Group</a> at the <a href="http://www.upf.edu">Universitat Pompeu Fabra</a>. 
			Originally, Mercury was created as part of the PhD work of Manuel Palacin but currently is maintained and used by different researchers and students. The Mercury team
			is formed by Manuel Palacin and Alex Bikfalvi under the supervision of Dr. Miquel Oliver.</p>
		<p>The Mercury Platform is a multi-purpose tool for probing the different Internet networks. Mercury can be used for different purposes:</p>
			<ul>
				<li>Detect my public IP</li>
				<li>Detect interconnection relationship between Autonomous Systems</li>
				<li>IP to ASN translation</li>
				<li>IP geolocation</li>
			</ul>
		<p>However, the main purpose of Mercury is to reveal how content providers interconnect with the access ISP networks. 
		In other words, we want to discover how web content providers (Google, Yahoo, Microsoft, Facebook, Twitter, your web site, ...) reach your home Internet subscription (Telefonica, AT&T, Comcast, Deutsche Telekom, Orange, O2, Vodafone, ...).
		With all this information we will identify the multiple interconnection strategies of these players.</p>			
		<p>If you want to know more about the Mercury project or if you want to collaborate improving the platform or creating a new extension, do not hesitate to <a href="mailto:manuel.palacin@upf.edu">contact us</a>.</p>

		<h1>Terms of service</h1>
		<p>To guarantee an optimal service of the Mercury Platform, we invite users to contact us for accommodating the system to your requirements.</p>
		<p>If this site is violating any law or if any information is wrong, please do not hesitate to contact us.</p>

	</div>
	<!-- /container -->
	
	<%@ include file="footer.jsp" %>
	
	
</body>
</html>