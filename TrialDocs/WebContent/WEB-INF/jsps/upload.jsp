<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Clinical Trail</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Lato:300,400,700'
	rel='stylesheet' type='text/css'>
<link href='custom.css' rel='stylesheet' type='text/css'>
</head>
<body>

	<div class="container">

		<div class="row">

			<div class="col-lg-8 col-lg-offset-2">

				<h1>Ampel BioSolutions Clinical Trial Documents</h1>

				<p class="lead">This is a database to store all the clinical
					trial documents.</p>

				<form id="contact-form" method="post"
					action="${pageContext.request.contextPath}/connect"
					enctype="multipart/form-data" role="form">

					<div class="messages"></div>

					<div class="controls">

						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_name">Firstname *</label> <input
										id="form_name" type="text" name="name" class="form-control"
										placeholder="Please enter your firstname *"
										required="required" data-error="Firstname is required.">
									<div class="help-block with-errors"></div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_lastname">Lastname *</label> <input
										id="form_lastname" type="text" name="surname"
										class="form-control"
										placeholder="Please enter your lastname *" required="required"
										data-error="Lastname is required.">
									<div class="help-block with-errors"></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_name">Sitenumber *</label> <input
										id="form_sitenumber" type="text" name="sitenumber"
										class="form-control"
										placeholder="Please enter your sitenumber *"
										required="required" data-error="Sitenumber is required.">
									<div class="help-block with-errors"></div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_studyname">Studyname *</label> <input
										id="form_studyname" type="text" name="studyname"
										class="form-control"
										placeholder="Please enter the studyname *" required="required"
										data-error="Studyname is required.">
									<div class="help-block with-errors"></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_name">Version *</label> <input
										id="form_version" type="text" name="version"
										class="form-control" placeholder="Please enter the version *"
										required="required" data-error="Version is required.">
									<div class="help-block with-errors"></div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_date">Date *</label> <input id="form_date"
										type="date" name="date" class="form-control"
										placeholder="mm/dd/yyyy *" required="required"
										data-error="Date field is required.">
									<div class="help-block with-errors"></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label for="form_message">Comments/Notes </label>
									<textarea id="form_message" name="message" class="form-control"
										placeholder="Additional notes.. " rows="4"
										data-error="Please,leave us a message."></textarea>
									<div class="help-block with-errors"></div>
								</div>
							</div>
							<input type="hidden" name="email"
								value="<%=request.getAttribute("email")%>"> <input
								type="hidden" name="role"
								value="<%=request.getAttribute("role")%>">
							<div class="col-md-12">
								<input type="file" name="file" id="fileUpload"> <input
									type="submit" class="btn btn-success btn-send" id="upload"
									value="Upload">
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<p class="text-muted">
									<strong>*</strong> These fields are required. Upload form
									template by <a href="http://ampelbiosolutions.com"
										target="_blank">Ampel BioSolutions</a>.
								</p>
							</div>
						</div>
					</div>

				</form>

			</div>

		</div>

	</div>
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#upload').bind("click", function() {
				var fileVal = $('#fileUpload').val();
				if (fileVal == '') {
					alert("Please upload file")
					return false;
				}else{
					return true;
				}
				
			});

		});
	</script>
</body>
</html>