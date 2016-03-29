<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href='<c:url value="/resources/css/upmstyle.css" />'>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>


<title>User Profile Management</title>
</head>
<body>
	<c:set var="users" value="${ users }"></c:set>
	<c:set var="msg" value="${ msg }"></c:set>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12">
								<h1>User Login</h1>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form:form id="login-form" action="processForm" method="post"
									modelAttribute="userLoginCommand" role="form"
									style="display: block;" >
									<c:if test="${ not empty failureMsg }">
										<div class="alert alert-danger">
											<strong>Failure! </strong> ${ failureMsg }
										</div>
									</c:if>
									<c:if test="${ not empty msg }">
										<div class="alert alert-success">
											<strong>Success! </strong>${ msg }
										</div>
									</c:if>
									<div class="form-group">
										<form:input type="text" name="loginId" path="loginId"
											tabindex="1" class="form-control" placeholder="Login ID"
											value=""></form:input>
										<form:errors path="loginId" class="error" />
									</div>
									<div class="form-group">
										<form:input type="text" name="otp" path="otp" tabindex="2"
											class="form-control" placeholder="OTP" value=""></form:input>
										<form:errors path="otp" class="error" />
									</div>
									<div class="form-group">
										<form:input type="password" name="password" path="password"
											tabindex="3" class="form-control" placeholder="Password"></form:input>
										<form:errors path="password" class="error" />
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-3 col-sm-offset-2">
												<input type="submit" name="login" id="login" tabindex="4"
													class="form-control btn btn-login" value="Log In">
											</div>

											<div class="col-sm-3 col-sm-offset-2">
												<input type="submit" name="registerUser" id="registerUser"
													tabindex="5" class="form-control btn btn-login"
													value="Register">
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href=""
														onclick="this.href='generateOTP?loginId='+document.getElementById('loginId').value"
														tabindex="6" class="link-style">Generate OTP</a>
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

</body>
</html>
