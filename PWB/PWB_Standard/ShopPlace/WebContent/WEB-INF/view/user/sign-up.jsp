<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/user/register" var="userRegister"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>
</head>
<body>
	<form action="${userRegister}" method="post">
		<label for="name">Name:</label>
		<input type="text" name="name">
		<label for="email">E-mail:</label>
		<input type="text" name="email">
		<label for="password">Password:</label>
		<input type="password" name="password">
		<label for="dateOfBirth">Date of Birth:</label>
		<input type="date" name="dateOfBirth">
		<input type="submit" value="Sign-Up">
	</form>
</body>
</html>