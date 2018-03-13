<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/" var="index" />
<c:url value="/profile/cnd" var="change_name_dob" />
<c:url value="/profile/cp" var="change_password" />
<c:url value="/head" var="head" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TecNow - User Profile</title>
</head>
<body>
	<c:import url="/head"></c:import>
	<p>That is your profile, ${user.name}!</p>
	<form action="${change_name_dob}" method="post">
	<!-- Data about Name and Date Of Birthday -->
		<div>
			<label for="name">Name</label>
			<input type="text" name="name" value="${user.name}">
			<p id="invalid-name">Name must contain from 2 to 60 characters!</p>
		</div>
		<div>
			<label for="dateOfBirth">Date Of Birth</label>
			<input type="date" name="dateOfBirth" value="${user.formattedDate}">
			<p id="invalid-dob">Date of Birth must be previous than now!</p>
		</div>
		<input type="submit" value="Change">
	</form>
	<form action="${change_password}" method="post">
	<!-- Form to Change User's Password -->
		<div>
			<label for="cpassword">Current Password</label>
			<input type="password" name="cpassword" required="required" >
			<p id="invalid-password">Invalid Current Password!</p>
		</div>
		<div>
			<label for="npassword">New Password</label>
			<input type="password" name="npassword" required="required" >
			<p id="invalid-npassword">New Password must contain between 2 and 20 characters!</p>
		</div>
		<div>
			<label for="cnpassword">Confirm Password</label>
			<input type="password" name="cnpassword" required="required" >
			<p id="invalid-confirmation">Invalid Password Confirmation!</p>
			<p id="password-changed">Password Successfully Changed!</p>
		</div>
		<input type="submit" value="Change">
	</form>
	<p>
		<a href="${index}">
			Go Home
		</a>
	</p>
</body>
<script>
var url = new URL(window.location.href);
var invalidNPassword = document.getElementById("invalid-npassword");
var invalidDOB = document.getElementById("invalid-dob");
var invalidName = document.getElementById("invalid-name");
var invalidPassword = document.getElementById("invalid-password");
var invalidConfirmation = document.getElementById("invalid-confirmation");
var passwordChanged = document.getElementById("password-changed");
var error = url.searchParams.get("message");
invalidPassword.style.display = error == "cpassword" ? "unset" : "none";
invalidConfirmation.style.display = error == "cnpassword" ? "unset" : "none";
passwordChanged.style.display = error == "password" ? "unset" : "none";
invalidName.style.display = error == "name" ? "unset" : "none";
invalidDOB.style.display = error == "dob" ? "unset" : "none";
invalidNPassword.style.display = error == "npassword" ? "unset" : "none";
</script>
</html>