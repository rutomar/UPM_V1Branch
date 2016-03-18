<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile Management</title>
</head>
<body>
	<div align="center">
		<h2>User Login</h2>
		<div>
			<form:form method="POST" modelAttribute="userLoginCommand" >
				<table>
					<tr>
						<td><form:label path="loginId">Login Id : </form:label></td>
						<td><form:input path="loginId" onChange="" /></td>
					</tr>
					<tr>
						<td><form:label path="otp">OTP : </form:label></td>
						<td><form:input path="otp" /></td>
					</tr>
					<tr>
						<td><form:label path="password">Password : </form:label></td>
						<td><form:password path="password" /></td>
					</tr>

				</table>
				<br>
				<div align="center">
					<input type="submit" value="Login" formaction="userLogin" /> 
					<input type="button" value="Register" /> 
					<input type="submit" value="Generate OTP" formaction="generateOTP" />
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>