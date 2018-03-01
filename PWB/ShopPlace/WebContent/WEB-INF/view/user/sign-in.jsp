<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/user/login" var="userLogin"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign In</title>
</head>
<body>
	<form action="${userLogin}" method="post">
		<label for="email">E-mail:</label>
		<input type="text" name="email">
		<label for="password">Password:</label>
		<input type="password" name="password">
		<input type="submit" value="Sign-In">
	</form>
</body>
</html>