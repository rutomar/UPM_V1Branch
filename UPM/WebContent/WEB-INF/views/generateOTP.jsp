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

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12">
								<h1>Generate OTP</h1>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form:form id="otp-generation-form" method="get"
									modelAttribute="otpCommand" role="form" style="display: block;">
									<div class="form-group">
										<form:input type="text" name="loginId" path="loginId"
											tabindex="1" class="form-control" placeholder="Login ID"
											value="${ loginId }" readonly="true"></form:input>
										<form:errors path="loginId" class="error" />

									</div>
									<div class="form-group">
										<form:input type="text" name="otp" path="otp" tabindex="2"
											class="form-control" placeholder="OTP" value="${otp}"
											readonly="true"></form:input>
										<form:errors path="otp"  class="error" />
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="<c:url value="userLogin" />" tabindex="6"
														class="link-style">GO BACK!</a>
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