<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<title>SignUp Page</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	/*$(document).ready(function() {

		$('#loginbutton').on('click', function() {
			window.open('${pageContext.request.contextPath}/');
		});
	});*/
</script>
</head>
<body>
	<div class="col-md-4 col-md-offset-4">
		<form class="form-horizontal"
			action='${pageContext.request.contextPath}/usercreated' method="POST">
			<fieldset>
				<div id="legend">
					<legend class="">Register</legend>
				</div>
				<div class="control-group">
					<!-- Username -->
					<label class="control-label" for="username">Username *</label>
					<div class="controls">
						<input type="text" id="username" name="username" placeholder=""
							class="input-xlarge" required="required"
							data-error="Username is required">
						<p class="help-block"></p>
					</div>
				</div>

				<div class="control-group">
					<!-- E-mail -->
					<label class="control-label" for="email">E-mail</label>
					<div class="controls">
						<input type="text" id="email" name="email" placeholder=""
							required="required" data-error="Email is required"
							class="input-xlarge">
					</div>
				</div>

				<div class="control-group">
					<!-- Password-->
					<label class="control-label" for="password">Password</label>
					<div class="controls">
						<input type="password" id="password" name="password"
							placeholder="" class="input-xlarge" pattern=".{4,}" required
							title="4 characters minimum">
						<p class="help-block">Password should be at least 4 characters</p>
					</div>
				</div>

				<div class="control-group">
					<!-- Password -->
					<label class="control-label" for="password_confirm">Password
						(Confirm)</label>
					<div class="controls">
						<input type="password" id="password_confirm"
							name="password_confirm" placeholder="" class="input-xlarge"
							pattern=".{4,}" required title="4 characters minimum">
						<p class="help-block">Please confirm password</p>
					</div>
				</div>
				<c:set var="nopass" scope="session"
					value="<%=session.getAttribute(\"nopass\")%>"></c:set>
				<c:if test="${nopass != null }">
					<p class="help-block">
						<c:out value="${nopass }" />
					</p>
				</c:if>
				<p class="help-block"></p>
				<p class="help-block"></p>
				<div class="control-group">

					<div class="form-group">
						<label class="control-label  col-sm-4" for="role">Select a
							Role</label>
						<div class="col-sm-4 col-md-4">
							<select id="role" name="role" class="form-control">
								<option>Reviewer</option>
								<option>Modifier</option>
							</select>
						</div>
					</div>
				</div>
				<p class="help-block"></p>
				<div class="control-group">
					<!-- Button -->
					<div class="controls">
						<button class="btn btn-success" id="registerbutton">Register</button>
					</div>
				</div>
			</fieldset>
		</form>
		<br />
		<div>
			<form action="${pageContext.request.contextPath}/">
				<button type=submit class="btn btn-success" id="loginbutton">Click
					here,to login</button>
			</form>
		</div>
	</div>
</body>
</html>