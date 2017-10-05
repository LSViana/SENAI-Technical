<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listing Contacts</title>
</head>
<body>
	<%--Create a new instance of AgendaDAO--%>
	<jsp:useBean id="contactDAO" class="br.sp.senai.informatica.noon.data.ContactDAO" />
	<%--Create a new DAO to register the items at database--%>
	<table>
		<c:forEach var="contact" items="${contactDAO.All}">
			<tr>
				<th>${contact.name }</th>
				<th>${contact.email }</th>
				<th>${contact.address }</th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>