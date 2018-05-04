<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<!-- URL declarations' area -->
<jstl:url value="/api/tasks/insert_task" var="api_insert_task" />
<!DOCTYPE html>
<html>
<head>
	<jstl:import url="/WEB-INF/templates/head.jsp" />
	<title>SENAI Keep | Insert Task</title>
</head>
<body>
	<form method="post" action="${api_insert_task}">
		<label>
			Title
			<input type="text" name="title">
		</label>
		<label>
			Description
			<textarea name="description" placeholder="Describe the task..."></textarea>
		</label>		
		<label>
			High Priority
			<input type="checkbox" name="high_priority">
		</label>
		<label>
			Conclusion Date
			<input type="datetime" name="conclusion_date">
		</label>
		<input type="submit" value="Save">
		</form>
</body>
</html>