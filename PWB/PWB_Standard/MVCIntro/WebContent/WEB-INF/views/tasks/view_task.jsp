<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<!-- URL declarations' area -->
<jstl:url value="/" var="home" />
<jstl:url value="/tasks/insert_task" var="insert_task" />
<jstl:url value="/api/tasks/list_tasks" var="list_tasks" />
<!DOCTYPE html>
<html>
<head>
	<jstl:import url="/WEB-INF/templates/head.jsp" />
	<title>SENAI Keep | Insert Task</title>
</head>
<body>
	<form>
		<label>
			Title
			<input type="text" name="title" disabled value="${task.title}">
		</label>
		<label>
			Description
			<textarea name="description" placeholder="Describe the task..." disabled>
				${task.description}
			</textarea>
		</label>
		<label>
			High Priority
			<input type="checkbox" name="high_priority" disabled ${task.highPriorityText}>
		</label>
		<label>
			Conclusion Date
			<input type="datetime" name="conclusion_date" disabled value="${task.conclusionDate}">
		</label>
		<a href="${home}">
			<input type="button" value="Home">
		</a>
		<a href="${insert_task}">
			<input type="button" value="Insert Tasks">
		</a>
		<a href="${list_tasks}">
			<input type="button" value="List Tasks">
		</a>
		</form>
</body>
</html>