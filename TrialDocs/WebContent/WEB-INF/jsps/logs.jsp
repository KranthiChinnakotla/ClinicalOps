<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<
<sql:query var="rs" dataSource="jdbc/ClinicalOpsDB">

select * from log_users;

</sql:query>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Logs</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Clinical Operations</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a
				href="${pageContext.request.contextPath }/downloadLogs">Download</a></li>
			<li><a href="${pageContext.request.contextPath}/">LogOut</a></li>

		</ul>
	</div>
	</nav>

	<div class="well well-lg">
		<div class="container">
			<table class="table">
				<thead>
					<tr>
						<th>Email</th>
						<th>Role</th>
						<th>Message</th>
						<th>Date</th>
					</tr>

				</thead>
				<tbody>
					<c:forEach var="row" items="${rs.rows }">
						<tr>
							<td>${row.email}</td>
							<td>${row.role}</td>
							<td>${row.message}</td>
							<td>${row.date}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>