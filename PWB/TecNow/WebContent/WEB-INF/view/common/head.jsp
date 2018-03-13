<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/" var="index" />
<c:url value="/assets/style.css" var="headCss" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${headCss}">
</head>
<body>
	<!-- Head Model -->
	<nav>
		<h1>
			<a href="${index}">
				TecNow
			</a>
		</h1>
		<p>Brazil</p>
	</nav>
</body>
</html>