<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<!-- URL declarations' area -->
<jstl:url value="/" var="index" />
<jstl:url value="/tasks/insert_task" var="insert_task" />
<!DOCTYPE html>
<html>
<head>
	<jstl:import url="/WEB-INF/templates/head.jsp" />
	<title>SENAI Keep | Welcome</title>
</head>
<body>
	<header>
		<ul>
			<li><a href="${index}">Home</a></li>
			<li><a href="${insert_task}">Insert Task</a></li>
		</ul>
	</header>
	<h1>Welcome to SENAI Keep</h1>
</body>
</html>