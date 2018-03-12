<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/sign-in" var="sign_in" />
<c:url value="/register" var="register" />
<c:url value="/head" var="head" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TecNow – User Sign-Up</title>
</head>
<body>
	<c:import url="/head"></c:import>
	<form action="${register}" method="post">
		<label for="name">Name</label>
		<input type="text" name="name">
		<label for="email">E-mail</label>
		<input type="text" name="email">
		<p id="email-exists">This e-mail already exists!</p>
		<label for="dateOfBirth">Date Of Birth</label>
		<input type="date" name="dateOfBirth">
		<p id="invalid-date">Invalid Date Of Birth!</p>
		<label for="password">Password</label>
		<input type="password" name="password">
		<input type="radio" name="gender" value="MALE" checked="checked" />
		<label for="gender">Male</label>
		<input type="radio" name="gender" value="FEMALE" />
		<label for="gender">Female</label>
		<input type="radio" name="gender" value="OTHER" />
		<label for="gender">Other</label>
		<input type="submit" value="Sign-Up">
	</form>
	<p>
		<a href="${sign_in}">
			Sign-In
		</a>
	</p>
</body>
<script>
var url = new URL(window.location.href);
var emailExists = document.getElementById("email-exists");
var invalidDate = document.getElementById("invalid-date");
var error = url.searchParams.get("error");
emailExists.style.display = error == "email" ? "unset" : "none";
invalidDate.style.display = error == "date" ? "unset" : "none";
</script>
</html>