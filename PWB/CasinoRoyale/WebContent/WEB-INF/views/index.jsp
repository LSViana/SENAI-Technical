<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="../head.jsp" />
<c:url value="/insert_user" var="insert_user" />
<c:url value="/users/list_users" var="list_users" />
<title>Casino Royale | Welcome</title>
</head>
<body>
	<header>
		<ul>
			<li>
				<a href="${insert_user}">
					Insert
				</a>
			</li>
			<li>
				<a href="${list_users}">
					List
				</a>
			</li>				
		</ul>
	</header>
	<div>
			<h3>Welcome to,</h3>
		<h1>Casino Royale</h1>
	</div>
</body>
</html>