<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>TracerouteAS info</title>
	<%@ include file="head.jsp" %>
</head>


<body>

	<%@ include file="header.jsp" %>

	<div class="container">
		<section>
			<h2>TracerouteAS details</h2>
			
			<display:table id="tracerouteAS" name="tracerouteAS" pagesize="10" export="false" class="table table-condensed table-hover">
					<display:column property="id" title="id" href="getTraceroute" paramId="id" paramProperty="id" sortable="true"/>
					<display:column property="timeStamp" title="timeStamp" sortable="true"/>
					
					<display:column property="srcPublicIp" title="origin Ip" sortable="true"/>
					<display:column title="srcCity" sortable="true">
						<a href="getTracerouteASStatsBySrcCity?srcCity=<s:url value="%{#attr.tracerouteAS.srcCity}"/>&srcCountry=<s:url value="%{#attr.tracerouteAS.srcCountry}"/>" >
							<s:url value="%{#attr.tracerouteAS.srcCity}"/>
						</a>
					</display:column>
					
					<display:column property="srcCountry" title="origin Country" href="getTracerouteASStatsBySrcCountry" paramId="srcCountry" paramProperty="srcCountry" sortable="true" />			
					<display:column property="srcAS" title="origin AS" href="getTracerouteASStatsBySrcAS" paramId="srcAS" paramProperty="srcAS" sortable="true" />
					<display:column property="srcASName" title="origin AS Name" sortable="true"/>
					<display:column property="dst" title="destination" href="getTracerouteASStatsByDst" paramId="dst" paramProperty="dst" sortable="true" />
					<display:column property="dstIp" title="destination Ip" sortable="true"/>
					<display:column title="destination City" sortable="true" >
						<a href="getTracerouteASStatsByDstCity?dstCity=<s:url value="%{#attr.tracerouteAS.dstCity}"/>&dstCountry=<s:url value="%{#attr.tracerouteAS.dstCountry}"/>" >
							<s:url value="%{#attr.tracerouteAS.dstCity}"/>
						</a>
					</display:column>
					
					
					<display:column property="dstCountry" title="destination Country" href="getTracerouteASStatsByDstCountry" paramId="dstCountry" paramProperty="dstCountry" sortable="true" />
					<display:column property="dstAS" title="destination AS" href="getTracerouteASStatsByDstAS" paramId="dstAS" paramProperty="dstAS" sortable="true" />
					<display:column property="dstASName" title="destination AS Name" sortable="true"/>
					
					<display:column title="view tr" >
						<a href="api/services/getTracerouteASById/<s:url value="%{#attr.tracerouteAS.id}"/>" ><img alt="view" src="img/icons/icn_alert_info.png" /></a>
					</display:column>

			</display:table>
			
			<display:table id="tracerouteASStats1" name="tracerouteAS.tracerouteASStats" export="false" class="table table-condensed">
					<display:column property="completed" title="completed" />
					<display:column property="flagsHEX" title="flags" />
					<display:column property="asHops" title="number AS Hops" />

			</display:table>
			
			<display:table id="tracerouteASStats2" name="tracerouteAS.tracerouteASStats" export="false" class="table table-condensed">
					<display:column property="s2sRels" title="Sibling Relationships" />
					<display:column property="p2cRels" title="Provider Relationships" />
					<display:column property="c2pRels" title="Customer Relationships" />
					<display:column property="p2pRels" title="Peering Relationships" />
					<display:column property="nfRels" title="Not Found Relationships" />
					<display:column property="ixpRels" title="Ixp Interconnection Relationships" />
			</display:table>
			
		</section>
		
		<section>
			<div class="row-fluid">  
				<div class="col-md-4"><div class="span3"><div id="chart_div0"></div></div></div>
				<div class="col-md-4"><div class="span3"><div id="chart_div1"></div></div></div>
				<div class="col-md-4"><div class="span3"><div id="chart_div11"></div></div></div>		
			</div> 
		</section>

		<section>
		

			
		
		<div class="row-fluid">
			<div class="col-md-6">
				<input type="button" value="Format Hops JSON" onclick="formatJson(document.getElementById('hops').value,'hops')">
				<s:textarea id="hops" label="Hops" value="%{tracerouteASHopsJson}" style="height: 800px; width: 90%;"/>
			</div>
			<div class="col-md-6">
				<input type="button" value="Format Relationships JSON" onclick="formatJson(document.getElementById('relations').value,'relations')">
				<s:textarea id="relations" label="Relationships" value="%{tracerouteASRelationshipsJson}" style="height: 800px; width: 90%;"/>
			</div>
		</div>
		
		
		
		
		<script type="text/javascript">
		
		function formatJson(val,id) {
			var retval = '';
			var str = val;
		    var pos = 0;
		    var strLen = str.length;
			var indentStr = '\t';
		    var newLine = '\n';
			var char = '';
			
			for (var i=0; i<strLen; i++) {
				char = str.substring(i,i+1);
				
				if (char == '}' || char == ']') {
					retval = retval + newLine;
					pos = pos - 1;
					
					for (var j=0; j<pos; j++) {
						retval = retval + indentStr;
					}
				}
				
				retval = retval + char;	
				
				if (char == '{' || char == '[' || char == ',') {
					retval = retval + newLine;
					
					if (char == '{' || char == '[') {
						pos = pos + 1;
					}
					
					for (var k=0; k<pos; k++) {
						retval = retval + indentStr;
					}
				}
			}
			document.getElementById(id).value=retval;
			return retval;
		}
		
		</script>
		
			<script type="text/javascript" src="https://www.google.com/jsapi"></script>
			    <script type="text/javascript">
		
		      // Load the Visualization API and the piechart package.
		      google.load('visualization', '1.0', {'packages':['corechart']});
		
		      // Set a callback to run when the Google Visualization API is loaded.
		      google.setOnLoadCallback(drawChart);
		
		      // Callback that creates and populates a data table,
		      // instantiates the pie chart, passes in the data and
		      // draws it.
		      function drawChart() {
		
		        // Create the data table.
		        var data = new google.visualization.DataTable();
		        data.addColumn('string', 'Hops');
		        data.addColumn('number', '#');
		        data.addRows([
		          ['asHops', <s:property value="tracerouteAS.tracerouteASStats.asHops"  escapeJavaScript="true" />]
		        ]);
		
		        // Set chart options
		        var options = {'title':'Number of AS Hops',
		                       'width':400,
		                       'height':300};
		
		        // Instantiate and draw our chart, passing in some options.
		        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div0'));
		        chart.draw(data, options);
		      }
		    </script>
		
			
			
		    <script type="text/javascript">
		
		      // Load the Visualization API and the piechart package.
		      google.load('visualization', '1.0', {'packages':['corechart']});
		
		      // Set a callback to run when the Google Visualization API is loaded.
		      google.setOnLoadCallback(drawChart);
		
		      // Callback that creates and populates a data table,
		      // instantiates the pie chart, passes in the data and
		      // draws it.
		      function drawChart() {
		
		        // Create the data table.
		        var data = new google.visualization.DataTable();
		        data.addColumn('string', 'Relationships');
		        data.addColumn('number', '#');
		        data.addRows([
		          ['s2sRels',  <s:property value="tracerouteAS.tracerouteASStats.s2sRels"  escapeJavaScript="true" />],
		          ['p2cRels', <s:property value="tracerouteAS.tracerouteASStats.p2cRels"  escapeJavaScript="true" />],
		          ['c2pRels', <s:property value="tracerouteAS.tracerouteASStats.c2pRels"  escapeJavaScript="true" />],
		          ['p2pRels', <s:property value="tracerouteAS.tracerouteASStats.p2pRels"  escapeJavaScript="true" />],
		          ['nfRels', <s:property value="tracerouteAS.tracerouteASStats.nfRels"  escapeJavaScript="true" />],
		          ['ixpRels', <s:property value="tracerouteAS.tracerouteASStats.ixpRels"  escapeJavaScript="true" />]
		        ]);
		
		        // Set chart options
		        var options = {'title':'AS Relationships',
		                       'width':400,
		                       'height':300};
		
		        // Instantiate and draw our chart, passing in some options.
		        var chart = new google.visualization.PieChart(document.getElementById('chart_div1'));
		        chart.draw(data, options);
		      }
		    </script>    
		
		
		    <script type="text/javascript">
		
		      // Load the Visualization API and the piechart package.
		      google.load('visualization', '1.0', {'packages':['corechart']});
		
		      // Set a callback to run when the Google Visualization API is loaded.
		      google.setOnLoadCallback(drawChart);
		
		      // Callback that creates and populates a data table,
		      // instantiates the pie chart, passes in the data and
		      // draws it.
		      function drawChart() {
		
		        // Create the data table.
		        var data = new google.visualization.DataTable();
		        data.addColumn('string', 'Relationships');
		        data.addColumn('number', '#');
		        data.addRows([
		    		          ['s2sRels',  <s:property value="tracerouteAS.tracerouteASStats.s2sRels"  escapeJavaScript="true" />],
		    		          ['p2cRels', <s:property value="tracerouteAS.tracerouteASStats.p2cRels"  escapeJavaScript="true" />],
		    		          ['c2pRels', <s:property value="tracerouteAS.tracerouteASStats.c2pRels"  escapeJavaScript="true" />],
		    		          ['p2pRels', <s:property value="tracerouteAS.tracerouteASStats.p2pRels"  escapeJavaScript="true" />],
		    		          ['nfRels', <s:property value="tracerouteAS.tracerouteASStats.nfRels"  escapeJavaScript="true" />],
		    		          ['ixpRels', <s:property value="tracerouteAS.tracerouteASStats.ixpRels"  escapeJavaScript="true" />]
		        ]);
		
		        // Set chart options
		        var options = {'title':'AS Relationships',
		                       'width':400,
		                       'height':300};
		
		        // Instantiate and draw our chart, passing in some options.
		        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div11'));
		        chart.draw(data, options);
		      }
		    </script>
		   
	 
    	</section>
    
    </div>
	<!-- /container -->
	
	<%@ include file="footer.jsp" %>
	
	
</body>
</html>