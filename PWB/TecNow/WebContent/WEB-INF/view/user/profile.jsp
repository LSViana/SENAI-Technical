<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/profile/cnd" var="change_name_dob" />
<c:url value="/" var="index" />
<c:url value="/head" var="head" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TecNow – User Profile</title>
</head>
<body>
	<c:import url="/head"></c:import>
	<p>That is your profile, ${user.name}!</p>
	<form action="${change_name_dob}" method="post">
		<label for="name">Name</label>
		<input type="text" name="name" value="${user.name}">
		<label for="dateOfBirth">Date Of Birth</label>
		<input type="date" name="dateOfBirth" value="${user.formattedDate}">
		<input type="submit" value="Change">
	</form>
	<p>
		<a href="${index}">
			Go Home
		</a>
	</p>
</body>
</html>