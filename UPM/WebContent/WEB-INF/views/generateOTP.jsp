<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h2>Generate OTP</h2>
		<div>
			<form:form method="GET" modelAttribute="userLoginCommand">
				<table>
					<tr>
						<td><form:label path="loginId">Login Id : </form:label></td>
						<td><form:input path="loginId"/></td>
					</tr>
					<tr>
						<td><form:label path="otp">OTP : </form:label></td>
						<td><form:input path="otp"/></td>
					</tr>

					<tr>
						<td colspan="1"></td>
						<td align="right"><a href="userLogin" >Go Back</a></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>