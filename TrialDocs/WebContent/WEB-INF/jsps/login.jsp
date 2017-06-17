<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
<div class="col-md-4 col-md-offset-4">
<form class="form-horizontal" action='${pageContext.request.contextPath}/upload' method="POST">
  <fieldset>
    <div id="legend">
      <legend class="">Login</legend>
    </div>
   
 
    <div class="control-group">
      <!-- E-mail -->
      <label class="control-label" for="email">E-mail</label>
      <div class="controls">
        <input type="text" id="email" name="email" placeholder="" class="input-xlarge">
        <p class="help-block">Please provide your E-mail</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- Password-->
      <label class="control-label" for="password">Password</label>
      <div class="controls">
        <input type="password" id="password" name="password" placeholder="" class="input-xlarge">
        <p class="help-block"></p>
      </div>
    </div>
 <div class="control-group">
      <!-- -->
      <div class="controls">
        <p>First time users click here: <a href="mailto:prathyu1618@gmail.com,kranthi2@hotmail.com?Subject=Hello%20again" target="_top">Send Mail</a>
        </p>
        <p class="help-block"></p>
      </div>
    </div>
    
 
    <div class="control-group">
      <!-- Button -->
      <div class="controls">
        <button class="btn btn-success">Login</button>
      </div>
    </div>
  </fieldset>
  </form>
 </div>
</body>
</html>