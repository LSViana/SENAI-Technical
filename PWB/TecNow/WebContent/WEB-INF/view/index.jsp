<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/sign-up" var="sign-up" />
<c:url value="/sign-in" var="sign-in" />
<c:url value="/sign-out" var="sign-out" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TecNow – Home</title>
</head>
<body>
	<c:choose>
		<c:when test="${user != null}">
			<p>
				<a href="${sign-out}">
					Sign Out
				</a>
			<p>
		</c:when>
		<c:otherwise>
			<p>
				<a href="${sign-in}">
					Sign In
				</a>
			</p>
			<p>
				<a href="${sign-up}">
					Sign Up
				</a>
			</p>
		</c:otherwise>
	</c:choose>
</body>
</html>