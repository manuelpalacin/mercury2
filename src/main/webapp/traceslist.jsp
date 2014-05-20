<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>Traceroute AS measurements</title>
	<%@ include file="head.jsp" %>
</head>


<body>

	<%@ include file="header.jsp" %>

<div class="container">
	
		<section>
			<h2>Last processed TracerouteAS</h2>
			
			<display:table id="tracerouteASes" name="tracerouteASes" pagesize="10" export="false" requestURI="/getLastTraceroutes" class="table table-condensed table-hover">
					<display:column property="id" title="id" href="getTraceroute" paramId="id" paramProperty="id" sortable="true"/>
					<display:column property="timeStamp" title="timeStamp" sortable="true"/>
					
					<display:column property="srcPublicIp" title="origin Ip" sortable="true"/>
					<display:column title="srcCity" sortable="true">
						<a href="getASTracerouteStatsByOriginCity?srcCity=<s:url value="%{#attr.tracerouteASes.srcCity}"/>&srcCountry=<s:url value="%{#attr.tracerouteASes.srcCountry}"/>" >
							<s:url value="%{#attr.tracerouteASes.srcCity}"/>
						</a>
					</display:column>
					
					<display:column property="srcCountry" title="origin Country" href="getASTracerouteStatsByOriginCountry" paramId="srcCountry" paramProperty="srcCountry" sortable="true" />			
					<display:column property="srcAS" title="origin AS" href="getASTracerouteStatsByOriginAS" paramId="srcAS" paramProperty="srcAS" sortable="true" />
					<display:column property="srcASName" title="origin AS Name" sortable="true"/>
					<display:column property="dst" title="destination" href="getASTracerouteStatsByDestination" paramId="dst" paramProperty="dst" sortable="true" />
					<display:column property="dstIp" title="destination Ip" sortable="true"/>
					<display:column title="destination City" sortable="true" >
						<a href="getASTracerouteStatsByDestinationCity?dstCity=<s:url value="%{#attr.tracerouteASes.dstCity}"/>&dstCountry=<s:url value="%{#attr.tracerouteASes.dstCountry}"/>" >
							<s:url value="%{#attr.tracerouteASes.dstCity}"/>
						</a>
					</display:column>
					
					
					
					
					<display:column property="dstCountry" title="destination Country" href="getASTracerouteStatsByDestinationCountry" paramId="dstCountry" paramProperty="dstCountry" sortable="true" />
					<display:column property="dstAS" title="destination AS" href="getASTracerouteStatsByDestinationAS" paramId="dstAS" paramProperty="dstAS" sortable="true" />
					<display:column property="dstASName" title="destination AS Name" sortable="true"/>
					
			
					<display:column title="view tr" >
						<a href="api/services/getTracerouteASById/<s:url value="%{#attr.tracerouteASes.id}"/>" ><img alt="view" src="img/icons/icn_alert_info.png" /></a>
					</display:column>

					
				<display:setProperty name="paging.banner.placement" value="bottom" />
			</display:table>
		</section>
	
		
		
	</div>
	<!-- /container -->
	
	<%@ include file="footer.jsp" %>
	
	
</body>
</html>