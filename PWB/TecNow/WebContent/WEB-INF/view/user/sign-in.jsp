<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/login" var="login" />
<c:url value="/sign-up" var="sign_up" />
<c:url value="/head" var="head" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TecNow - User Sign-In</title>
</head>
<body>
	<c:import url="/head"></c:import>
	<form action="${login}" method="post">
	<!-- Form to authenticate User -->
		<div>
			<label for="email">E-mail</label>
			<input type="text" name="email">
		</div>
		<div>
			<label for="password">Password</label>
			<input type="password" name="password">
		</div>
		<input type="submit" value="Sign-In">
		<p id="auth-error">Invalid e-mail or password!</p>
	</form>
	<p>
		<a href="${sign_up}">
			Sign-Up
		</a>
	</p>
</body>
<script>
var url = new URL(window.location.href);
var emailExists = document.getElementById("auth-error");
var error = url.searchParams.get("message");
if(error != undefined && error == "auth") {
	emailExists.style.display = "unset";
}
else {
	emailExists.style.display = "none";
}
</script>
</html>