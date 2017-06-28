<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Lato:300,400,700'
	rel='stylesheet' type='text/css'>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
#lab {
	font-size: 20px;
}
</style>
<title>Uploaded Success</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Clinical Operations</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="${pageContext.request.contextPath }/mainpage?email=<%= request.getAttribute("email")%>&role=<%= request.getAttribute("role")%>&password=">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/">LogOut</a></li>
			
		</ul>
	</div>
	</nav>

	<div class="well well-lg">
		<div class="row">
			<div class="col-md-6">
				<label class="label label-success" id="lab"><%=request.getAttribute("success")%></label>
			</div>
		</div>
		<br></br> <br></br>

	</div>
</body>
</html>