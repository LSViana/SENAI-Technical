<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/sign-up" var="sign_up" />
<c:url value="/sign-in" var="sign_in" />
<c:url value="/profile" var="profile" />
<c:url value="/logout" var="logout" />
<c:url value="/head" var="head" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TecNow – Home</title>
</head>
<body>
	<c:import url="/head"></c:import>
	<c:choose>
		<c:when test="${user != null}">
			<p>Welcome, ${user.name}!</p>
			<p>
				<a href="${logout}">
					Sign Out
				</a>
			<p>
			<p>
				<a href="${profile}">
					Profile
				</a>
			</p>
		</c:when>
		<c:otherwise>
			<p>
				<a href="${sign_in}">
					Sign In
				</a>
			</p>
			<p>
				<a href="${sign_up}">
					Sign Up
				</a>
			</p>
		</c:otherwise>
	</c:choose>
</body>
</html>