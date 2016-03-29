<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href='<c:url value="/resources/css/upmstyle.css" />'>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<title>User Profile Management</title>
<style>
.btn-file {
	position: relative;
	overflow: hidden;
}

.btn-file input[type=file] {
	position: absolute;
	top: 0;
	right: 0;
	min-width: 100%;
	min-height: 100%;
	font-size: 100px;
	text-align: right;
	filter: alpha(opacity = 0);
	opacity: 0;
	outline: none;
	background: white;
	cursor: inherit;
	display: block;
}

#img-upload {
	width: 50%;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12">
								<h1>User Registration</h1>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form:form id="user-register-form" method="post"
									action="processUserProfile" modelAttribute="userProfileCommand"
									role="form" style="display: block;"
									enctype="multipart/form-data">
									<div class="form-group">
										<form:input type="text" name="loginId" path="loginId"
											tabindex="1" class="form-control" placeholder="Login ID"
											value=""></form:input>
										<form:errors path="loginId" class="error" />
									</div>
									<div class="form-group">
										<form:input type="password" name="password" path="password"
											tabindex="2" class="form-control" placeholder="Password"></form:input>
										<form:errors path="password" class="error" />
									</div>
									<div class="form-group">
										<form:input type="text" name="userName" path="userName"
											tabindex="3" class="form-control" placeholder="Username"
											value=""></form:input>
										<form:errors path="userName" class="error" />
									</div>
									<div class="form-group">
										<form:input type="email" name="emailId" path="emailId"
											tabindex="4" class="form-control" placeholder="Email Address"
											value=""></form:input>
										<form:errors path="emailId" class="error" />
									</div>
									<div class="form-group text-center panel-heading">
										<h4>Address</h4>
									</div>
									<hr>
									<div class="form-group">
										<form:input type="text" name="houseNo" path="houseNo"
											tabindex="5" class="form-control" placeholder="House No."></form:input>
										<form:errors path="houseNo" class="error" />
									</div>
									<div class="form-group">
										<form:input type="text" name="street" path="street"
											tabindex="6" class="form-control" placeholder="Street"></form:input>
										<form:errors path="street" class="error" />
									</div>
									<div class="form-group">
										<form:input type="text" name="city" path="city" tabindex="7"
											class="form-control" placeholder="City"></form:input>
										<form:errors path="city" class="error" />
									</div>
									<div class="form-group">
										<form:select name="state" path="state" id="state" tabindex="8"
											class="form-control" required="required">
											<form:option value="0" label="-Select-" />
											<form:options items="${states}" itemValue="stateId"
												itemLabel="stateName" />
										</form:select>
										<form:errors path="state" class="error" />
									</div>
									<div class="form-group">
										<label for="country">Country</label>
										<form:input type="text" name="country" path="country"
											tabindex="9" class="form-control" placeholder="Country"
											readonly="true" value="IN"></form:input>
										<form:errors path="country" class="error" />
									</div>

									<div class="form-group">
										<label for="file">Upload profile picture</label> <input
											type="file" name="file" tabindex="10" class="form-control" />
										<form:errors path="file" class="error" />
									</div>

									<%-- <div class="form-group">
										<label>Profile Picture</label>
										<!-- <span class="input-group-btn"> <span
											class="btn btn-default btn-file">  Browse… -->
										<input type="file" id="imgInp" name="file" tabindex="10"
											class="form-control">
										<form:errors path="file" class="error"  />
										<!-- </span> -->
										<!-- </span>  -->
										<!-- <input type="text" class="form-control" readonly> -->
										<img id='img-upload' class="img-thumbnail" />
									</div>
 --%>
									<div class="form-group ">
										<div class="form-actions">
											<div class="col-lg-7">
											<button type="submit" name="createUser" id="register-submit"
												tabindex="11" class="btn  btn-success">Register Now</button>
											<button type="submit" formnovalidate="formnovalidate"
												tabindex="12" class="btn" name="clear">Cancel</button>
										</div>
										<div class="col-lg-5">
												<div class="text-center">
													<a href="<c:url value="userLogin" />" tabindex="6"
														class="link-style">Go back to User Login!</a>
												</div>
											</div>
										</div>
									</div>
									
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(
				function() {
					$(document).on(
							'change',
							'.btn-file :file',
							function() {
								var input = $(this), label = input.val()
										.replace(/\\/g, '/')
										.replace(/.*\//, '');
								input.trigger('fileselect', [ label ]);
							});

					$('.btn-file :file').on(
							'fileselect',
							function(event, label) {

								var input = $(this).parents('.input-group')
										.find(':text'), log = label;

								if (input.length) {
									input.val(log);
								} else {
									if (log)
										alert(log);
								}

							});
					function readURL(input) {
						if (input.files && input.files[0]) {
							var reader = new FileReader();

							reader.onload = function(e) {
								$('#img-upload').attr('src', e.target.result);
							}

							reader.readAsDataURL(input.files[0]);
						}
					}

					$("#imgInp").change(function() {
						readURL(this);
					});
				});
	</script>
</body>
</html>