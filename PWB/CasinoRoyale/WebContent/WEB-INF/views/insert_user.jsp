<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="../head.jsp" />
<c:url value="/users/insert_user" var="insert_user" />
<c:url value="/list_users" var="list_users" />
<c:url value="/" var="index" />
<title>Casino Royale | Inserting User</title>
<style>
form {
	display: flex;
	flex-flow: column;
}
</style>
</head>
<body>
	<form method="post" action="${insert_user}">
		<input style="display: none" type="text" name="id" value="${user.id}" />
		<label>
			First Name:
			<input type="text" name="first_name" value="${user.firstName}" />
		</label>
		<label>
			Last Name:
			<input type="text" name="last_name" value="${user.lastName}" />
		</label>
		<label>
			Gender:
		</label>
			<label for="gender">
				<c:choose>
					<c:when test="${user.gender == 'MALE'}">
						<input type="radio" name="gender" value="Male" checked />
					</c:when>
					<c:otherwise>
						<input type="radio" name="gender" value="Male" />
					</c:otherwise>
				</c:choose>				
				Male
			</label>
			<label for="gender">
				<c:choose>
					<c:when test="${user.gender == 'FEMALE'}">
						<input type="radio" name="gender" value="Male" checked />
					</c:when>
					<c:otherwise>
						<input type="radio" name="gender" value="Male" />
					</c:otherwise>
				</c:choose>
				Female
			</label>
			<label for="gender">
				<c:choose>
					<c:when test="${user.gender == 'OTHER'}">
						<input type="radio" name="gender" value="Male" checked />
					</c:when>
					<c:otherwise>
						<input type="radio" name="gender" value="Male" />
					</c:otherwise>
				</c:choose>
				Other
			</label>
		<label>
			<c:choose>
				<c:when test="${user.smoker}">
					<input type="checkbox" name="smoker" checked />
				</c:when>
				<c:otherwise>
					<input type="checkbox" name="smoker" />
				</c:otherwise>
			</c:choose>
			Smoker
		</label>
		<input type="submit" value="Save">
	</form>
	<a href="${index}">
		<input type="button" value="Index">
	</a>
</body>
</html>