<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>Mercury</title>
	<%@ include file="head.jsp" %>
</head>


<body>

	<%@ include file="header.jsp" %>

	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<div class="col-md-4">
				<h2>Participate!</h2>
				<img alt="world" style="width: 300px; height: 200px;" src="img/world-home.png">
				<p>You can improve the Mercury database sending us data from your computer. 
					You just need to download our Desktop client (Windows version) and execute it.</p>
				<p>
					<a class="btn btn-default" href="#" role="button">Download!
						&raquo;</a>
				</p>
			</div>
			<div class="col-md-4">
				<h2>View data!</h2>
				<img alt="stats" style="width: 300px; height: 200px;" src="img/stats-home.png">
				<p>You can browse information about the interconnection between ISPs. 
					We focus on identifying how content providers reach the access ISPs.</p>
				<p>
					<a class="btn btn-default" href="getLastTraceroutes" role="button">View data!
						&raquo;</a>
				</p>
			</div>
			<div class="col-md-4">

				<h2>Develop!</h2>
				<img alt="cloud" style="width: 300px; height: 200px;" src="img/cloud-home.png">
				<p>You can collaborate developing new extensions for Mercury. 
					Visit our open API to know what can you do!</p>
				<p>
					<a class="btn btn-default" href="api.jsp" role="button">View API
						&raquo;</a>
				</p>
			</div>
		</div>

		


	</div>
	<!-- /container -->
	
	<%@ include file="footer.jsp" %>
	
	
</body>
</html>