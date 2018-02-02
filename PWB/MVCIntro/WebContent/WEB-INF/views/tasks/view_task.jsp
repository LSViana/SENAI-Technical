<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<!-- URL declarations' area -->
<jstl:url value="/tasks/view_task" var="view_task" />
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
			<input type="checkbox" name="high_priority" disabled value="${task.highPriority}">
		</label>
		<label>
			Conclusion Date
			<input type="datetime" name="conclusion_date" disabled value="${task.conclusionDate}">
		</label>
		<a href="${view_task}">
			<input value="Home">
		</a>
		</form>
</body>
</html>