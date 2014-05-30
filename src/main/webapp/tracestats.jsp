<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>Statistics</title>
	<%@ include file="head.jsp" %>
</head>


<body>

	<%@ include file="header.jsp" %>

	<div class="container">
		<section>
			<h2>Traceroute Stats!</h2>
			
			<h3>General info:</h3>
			<display:table id="tracerouteASAggregatedStats1" name="tracerouteASAggregatedStats" export="false" class="table table-striped">
					<display:column property="info" title="info" />
					<display:column property="numberASTracerouteStats" title="number ASTraceroute Stats" />
					<display:column property="numberCompletedASTracerouteStats" title="number Completed ASTraceroute Stats" />
					
					<display:column property="percentageCompleted" title="percentage Completed" />
					<display:column property="srcAS" title="src AS" />
					<display:column property="dst" title="dst" />
					<display:column property="dstAS" title="dst AS" />		
					
					<display:column property="srcCity" title="src City" />
					<display:column property="srcCountry" title="src Country" />
					<display:column property="dstCity" title="dst City" />
					<display:column property="dstCountry" title="dst Country" />	
			</display:table>
			
			<h3>Average:</h3>
			<display:table id="tracerouteASAggregatedStats2" name="tracerouteASAggregatedStats" export="false" class="table table-striped">
					<display:column property="averageNumberASHops" title="average Number AS Hops" />
					<display:column property="averageNumberSiblingRelationships" title="Sibling Relationships" />
					<display:column property="averageNumberProviderRelationships" title="Provider Relationships" />
					<display:column property="averageNumberCustomerRelationships" title="Customer Relationships" />
					<display:column property="averageNumberPeeringRelationships" title="Peering Relationships" />
					<display:column property="averageNumberNotFoundRelationships" title="Not Found Relationships" />
					<display:column property="averageNumberIxpInterconnectionRelationships" title="Ixp Interconnection Relationships" />
				
			</display:table>
			
			<h3>Standard deviation:</h3>
			<display:table id="tracerouteASAggregatedStats3" name="tracerouteASAggregatedStats" export="false" class="table table-striped">
					<display:column property="stdeviationNumberASHops" title="stdeviation Number AS Hops" />
					<display:column property="stdeviationNumberSiblingRelationships" title="Sibling Relationships" />
					<display:column property="stdeviationNumberProviderRelationships" title="Provider Relationships" />
					<display:column property="stdeviationNumberCustomerRelationships" title="Customer Relationships" />
					<display:column property="stdeviationNumberPeeringRelationships" title="Peering Relationships" />
					<display:column property="stdeviationNumberNotFoundRelationships" title="Not Found Relationships" />
					<display:column property="stdeviationNumberIxpInterconnectionRelationships" title="Ixp Interconnection Relationships" />
				
			</display:table>
				
			<h3>Median:</h3>
			<display:table id="tracerouteASAggregatedStats4" name="tracerouteASAggregatedStats" export="false" class="table table-striped">
					<display:column property="medianNumberASHops" title="median Number AS Hops" />
					<display:column property="medianNumberSiblingRelationships" title="Sibling Relationships" />
					<display:column property="medianNumberProviderRelationships" title="Provider Relationships" />
					<display:column property="medianNumberCustomerRelationships" title="Customer Relationships" />
					<display:column property="medianNumberPeeringRelationships" title="Peering Relationships" />
					<display:column property="medianNumberNotFoundRelationships" title="Not Found Relationships" />
					<display:column property="medianNumberIxpInterconnectionRelationships" title="Ixp Interconnection Relationships" />

			</display:table>
		</section>
		
		<section>
		    <div id="chart_div" style="width: 900px; height: 500px;"></div>
			
			<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		    <script type="text/javascript">
		      google.load('visualization', '1', {packages: ['corechart']});
		    </script>
		    <script type="text/javascript">
				function drawVisualization() {
				  // Create and populate the data table.
				  
				  
				  
				var data = google.visualization.arrayToDataTable([
						['ID', 'IQR', '', '', '', 'Median', 'Average'],
						  
						['Number AS hops', 
							   	<s:property value="tracerouteASAggregatedStats.q1NumberASHops"  escapeJavaScript="true" />-(<s:property value="tracerouteASAggregatedStats.q3NumberASHops"  escapeJavaScript="true" />-<s:property value="tracerouteASAggregatedStats.q1NumberASHops"  escapeJavaScript="true" />)*1.5, 
							   	<s:property value="tracerouteASAggregatedStats.q1NumberASHops"  escapeJavaScript="true" />, 
							   	<s:property value="tracerouteASAggregatedStats.q3NumberASHops"  escapeJavaScript="true" />, 
							   	<s:property value="tracerouteASAggregatedStats.q3NumberASHops"  escapeJavaScript="true" />+(<s:property value="tracerouteASAggregatedStats.q3NumberASHops"  escapeJavaScript="true" />-<s:property value="tracerouteASAggregatedStats.q1NumberASHops"  escapeJavaScript="true" />)*1.5, 
							   	<s:property value="tracerouteASAggregatedStats.medianNumberASHops"  escapeJavaScript="true" />, 
							   	<s:property value="tracerouteASAggregatedStats.averageNumberASHops"  escapeJavaScript="true" />]
						  
		
						  // Treat first row as data as well.
						]);
				  // Create and draw the visualization.
				  var ac = new google.visualization.ComboChart(document.getElementById('chart_div'));
				  ac.draw(data, {
					title : 'Box Plot with Median and Average',
					width: 900,
					height: 400,
					vAxis: {title: "Value"},
					hAxis: {title: "Serie ID"},
					series: { 0: {type: "candlesticks"}, 1: {type: "line", pointSize: 10, lineWidth: 0 }, 2: {type: "line", pointSize: 10, lineWidth: 0, color: 'black' } }
				  });
				}
				google.setOnLoadCallback(drawVisualization);
		    </script>
		</section>
		
		<section>
	
				<h3>citySrcMatchings:</h3>
				<display:table id="citySrcMatchings" name="tracerouteASAggregatedStats.citySrcMatchings" pagesize="20" export="false" class="table table-striped">
						<display:column property="city" title="city" sortable="true"/>
						<display:column property="country" title="country" sortable="true"/>
						<display:column property="number" title="number" sortable="true"/>
				</display:table>
				<h3>cityDstMatchings:</h3>
				<display:table id="cityDstMatchings" name="tracerouteASAggregatedStats.cityDstMatchings" pagesize="20" export="false"  class="table table-striped">
						<display:column property="city" title="city" sortable="true"/>
						<display:column property="country" title="country" sortable="true"/>
						<display:column property="number" title="number" sortable="true"/>
				</display:table>
				<h3>countrySrcMatchings:</h3>
				<display:table id="countrySrcMatchings" name="tracerouteASAggregatedStats.countrySrcMatchings" pagesize="20" export="false"  class="table table-striped">
						<display:column property="country" title="country" sortable="true"/>
						<display:column property="number" title="number" sortable="true"/>
				</display:table>
				<h3>countryDstMatchings:</h3>
				<display:table id="countryDstMatchings" name="tracerouteASAggregatedStats.countryDstMatchings" pagesize="20" export="false"  class="table table-striped">
						<display:column property="country" title="country" sortable="true"/>
						<display:column property="number" title="number" sortable="true"/>
				</display:table>
				
				
				<h3>ipSrcMatchings:</h3>
				<display:table id="ipSrcMatchings" name="tracerouteASAggregatedStats.ipSrcMatchings" pagesize="20" export="false"  class="table table-striped">
						<display:column property="ip" title="ip" sortable="true"/>
						<display:column property="number" title="number" sortable="true"/>
				</display:table>
				<h3>ipDstMatchings:</h3>
				<display:table id="ipDstMatchings" name="tracerouteASAggregatedStats.ipDstMatchings" pagesize="20" export="false"  class="table table-striped">
						<display:column property="ip" title="ip" sortable="true"/>
						<display:column property="number" title="number" sortable="true"/>
				</display:table>
				<h3>asSrcMatchings:</h3>
				<display:table id="asSrcMatchings" name="tracerouteASAggregatedStats.asSrcMatchings" pagesize="20" export="false"  class="table table-striped">
						<display:column property="asNumber" title="asNumber" sortable="true"/>
						<display:column property="number" title="number" sortable="true"/>
				</display:table>
				<h3>asDstMatchings:</h3>
				<display:table id="asDstMatchings" name="tracerouteASAggregatedStats.asDstMatchings" pagesize="20" export="false"  class="table table-striped">
						<display:column property="asNumber" title="asNumber" sortable="true"/>
						<display:column property="number" title="number" sortable="true"/>
				</display:table>
		</section>	
		
	</div><!-- End container -->
	
	<%@ include file="footer.jsp" %>
	
	
</body>
</html>