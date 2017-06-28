<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
	<div class="col-md-4 col-md-offset-4">
		<form class="form-horizontal"
			action='${pageContext.request.contextPath}/home' method="POST">
			<fieldset>
				<div id="legend">
					<legend class="">Register</legend>
				</div>


				<div class="control-group">
					<!-- E-mail -->
					<label class="control-label" for="email">Admin Id</label>
					<div class="controls">
						<input type="text" id="adminid" name="adminid"
							placeholder="Enter your Admin User Id" class="input-xlarge" size="35">
					</div>
				</div>

				<div class="control-group">
					<!-- Password-->
					<label class="control-label" for="password">Password</label>
					<div class="controls">
						<input type="password" id="password" name="password"
							placeholder="Enter your Admin password" class="input-xlarge" size="35">
						<p class="help-block"></p>
					</div>
				</div>
				<div>
					<c:set var='admincred' scope='session'
						value="<%=request.getAttribute(\"admincred\")%>" />
					<c:if test="${admincred != null }">
						<p class="help-block">
							<c:out value="${admincred }" />
						</p>

					</c:if>
				</div>
				<div class="control-group">
					<div class="controls">
						<button class="btn btn-success">Register</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>