<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Using Expression Language</title>
</head>
<body>
	<h1>OI</h1>
	<jsp:useBean id="agenda" class="br.senai.sp.informatica.webagenda.model.Agenda" />
	{$agenda.Name }
</body>
</html>