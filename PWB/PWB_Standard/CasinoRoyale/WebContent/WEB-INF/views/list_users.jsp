<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="../head.jsp" />
<c:url value="/insert_user" var="insert_user" />
<c:url value="/users/edit_user" var="edit_user" />
<c:url value="/" var="index" />
<title>Casino Royale | List Users</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>Full Name</th>
				<th>Gender</th>
				<th>Smoker</th>
				<th>Edit</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.firstName} ${user.lastName}</td>
					<td>${user.gender}</td>
					<td>${user.smoker}</td>
					<td>
						<a href="${edit_user}?id=${user.id}">
							Edit
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="${insert_user}">
		<button>Insert Users</button>
	</a>
	<a href="${index}">
		<button>Index</button>
	</a>
</body>
</html>