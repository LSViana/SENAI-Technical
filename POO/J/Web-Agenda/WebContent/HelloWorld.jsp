<%@page import="br.senai.sp.informatica.webagenda.model.Agenda"%>
<%@page import="br.senai.sp.informatica.webagenda.data.AgendaDAO"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.LocalTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome, Guest!</title>
</head>
<body>
	<%--Creating a new JSP commentary--%>
	
	<%--How to create a new String variable--%>
	<%
		String message = "<h1>Welcome to the Agenda!</h1>";
	%>
	
	<%--The standard output stream here is represented by the PrintWriter called by the names 'out' or '='--%>
	<%
		out.println(message);
	%>
	
	<%--It shows the current date in a formatted way--%>
	<%
		out.println(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
	%>
	
	<%--It shows the current time in a formatted way--%>
	<%
		out.println(LocalTime.now().format(DateTimeFormatter.ISO_TIME));
	%>
</body>
</html>