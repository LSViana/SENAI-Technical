<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/" var="index" />
<c:url value="/head" var="head" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TecNow - Error ${errorCode}</title>
</head>
<body>
	<c:import url="/head"></c:import>
	<h1>Error ${errorCode}</h1>
	<p>${errorMessage}</p>
	<p>
		<a href="${index}">
			Go Home
		</a>
	</p>
</body>
</html>