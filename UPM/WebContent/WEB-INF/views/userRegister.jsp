<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile Management</title>
</head>
<body>
<div align="center">
<h2>User Registration</h2>
<div>
	<form:form method="POST" modelAttribute="userProfileCommand">
	
			<form:label path="loginId">Login Id : </form:label> 
			<form:input path="loginId" id="loginId" />
			
			<form:label path="password">Password : </form:label> 
			<form:input path="password" id="password" /><br/><
			
			<form:label path="userName">User Name : </form:label> 
			<form:input path="userName" id="userName" /><br><
			
			<form:label path="emailId">Email id : </form:label> 
			<form:input path="emailId" id="emailId" /><br><
			
			<input type="text" id="address">Address  
			
			<form:label path="houseNo">House No : </form:label> 
			<form:input path="houseNo" id="houseNo" /><br><
			
			<form:label path="street">Street : </form:label> 
			<form:input path="street" id="street" /><br><
			
			<form:label path="city">City : </form:label> 
			<form:input path="city" id="city" /><br><
			
			<form:label path="state">State : </form:label> 
			<form:select path="state" id="state" /><br><
			
			<form:label path="country">Country : </form:label> 
			<form:input path="country" id="country" readonly="true" disabled="true"/><br><
			
			
				
			<input type="submit" value="submit" formaction="registerUser" /> 
			<input type="submit" value="clear" /> 
			
					
			Profile Picture<img src="" >  
			<input type="submit" value="clear" /> 
			
	
	
	</form:form>
	</div>
	</div>
</body>
</html>