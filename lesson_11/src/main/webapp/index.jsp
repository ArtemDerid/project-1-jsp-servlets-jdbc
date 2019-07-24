<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<form action="registration" method="post">
		<label for="email">Email</label><br> <input name="email"><br>
		<label for="password">Password</label><br> <input name="password"><br>
		<label for="telephoneNumber">Telephone Number</label><br> <input name="telephoneNumber"><br>
		<label for="firstName">First Name</label><br> <input name="firstName"><br> 
		<label for="lastName">Last Name</label><br> <input name="lastName"><br> 
		<input type="submit" value="submit">
	</form>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
