<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="../head.jsp" />
<c:url value="/insert_user" var="insert_user" />
<c:url value="/list_users" var="list_users" />
<title>Casino Royale | Inserting User</title>
</head>
<body>
	<form method="post" action="${insert_user}">
		<label>
			First Name:
			<input type="text" name="first_name">
		</label>
		<label>
			Last Name:
			<input type="text" name="last_name">
		</label>
		<label>
			Gender:
			<input type="checkbox" name="gender" value="Male">
			<input type="checkbox" name="gender" value="Female">
			<input type="checkbox" name="gender" value="Other">
		</label>
		<label>
			Smoker:
			<input type="checkbox" name="smoker">
		</label>
		<input type="button" value="Save">
	</form>
</body>
</html>