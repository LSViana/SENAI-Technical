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
<title>TecNow - User Sign-Up</title>
</head>
<body>
	<c:import url="/head"></c:import>
	<form action="${register}" method="post">
	<!-- Form to register new User -->
		<div>
			<label for="name">Name</label>
			<input type="text" name="name">
			<p id="invalid-name">The name must contain between 2 and 60 characters!</p>
		</div>
		<div>
			<label for="email">E-mail</label>
			<input type="text" name="email">
			<p id="email-exists">This e-mail already exists!</p>
		</div>
		<div>
			<label for="dateOfBirth">Date Of Birth</label>
			<input type="date" name="dateOfBirth">
			<p id="invalid-date">Invalid Date Of Birth!</p>
		</div>
		<div>
			<label for="password">Password</label>
			<input type="password" name="password">
			<p id="invalid-password">The password must contain between 2 and 20 characters!</p>
		</div>
		<section>
			<input type="radio" name="gender" value="MALE" checked="checked" />
			<label for="gender">Male</label>
			<input type="radio" name="gender" value="FEMALE" />
			<label for="gender">Female</label>
			<input type="radio" name="gender" value="OTHER" />
			<label for="gender">Other</label>
		</section>
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
var invalidName = document.getElementById("invalid-name");
var invalidPassword = document.getElementById("invalid-password");
var error = url.searchParams.get("message");
emailExists.style.display = error == "email" ? "unset" : "none";
invalidDate.style.display = error == "date" ? "unset" : "none";
invalidName.style.display = error == "name" ? "unset" : "none";
invalidPassword.style.display = error == "password" ? "unset" : "none";
</script>
</html>