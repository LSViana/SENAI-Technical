<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<jstl:url value="/" var="home" />
<jstl:url value="/api/tasks/delete_task" var="delete_task" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SENAI Keep | List All</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Description</th>
				<th>High Priority</th>
				<th>Remove Task</th>
			</tr>
		</thead>
		<tbody>
			<jstl:forEach items="${tasks}" var="task">
				<tr>
					<td>${task.id}</td>
					<td>${task.title}</td>
					<td>${task.description}</td>
					<td><input type="checkbox" disabled ${task.highPriorityText}></td>
					<td><a href="${delete_task}?id=${task.id}">Delete</a></td>
				</tr>
			</jstl:forEach>
		</tbody>
	</table>
	<a href="${home}">
			<input type="button" value="Home">
	</a>
</body>
</html>