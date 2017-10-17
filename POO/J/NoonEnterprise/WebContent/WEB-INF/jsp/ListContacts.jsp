<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listing Contacts</title>
<link href="css/jquery-ui.min.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<style type="text/css">
	table, tr, td {
		border: solid .5px;
		border-collapse: collapse;
	}
	td, th {
		padding: 10px;
	}
</style>
</head>
<body>
	<%--Importing other pages to this, useful to keep all files synchronized--%>
	<c:import url="Header.jsp"></c:import>
	<%--The DAO is now allocated at pure Java files to implement MVC Design Pattern--%>
	<%--Create a new instance of AgendaDAO--%>
	<%--<jsp:useBean id="dao" class="br.sp.senai.informatica.noon.data.ContactDAO" />
	<%--Create a new DAO to register the items at database--%>
	<table>
		<tr>
			<th>Name</th>
			<th>E-mail</th>
			<th>Address</th>
			<th>Date of Birthday</th>
			<th>Remove</th>
		</tr>
		<!--Avoid to use DAO at JSP page to get all contacts, it might be made in the following way-->
		<c:forEach var="contact" items="${ contacts }" varStatus="id">
		<%--<c:forEach var="contact" items="${ dao.all }" varStatus="id">--%>
			<tr style="background-color : #${ id.count % 2 == 0 ? 'EEEEEE' : 'CCCCCC' }">
				<td>${ contact.name }</td>
				<td>
					<c:if test="${ not empty contact.email }">
						<a href="mailto:${ contact.email }">${ contact.email }</a>
					</c:if>
					<c:if test="${ empty contact.email }">
						E-mail not supplied
					</c:if>
				</td>
				<td>${ contact.address }</td>
				<td>${ contact.dateOfBirthday }</td>
				<td>
					<a href="mvc?task=RemoveContact&id=${ contact.id }">Remove</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%--Importing footer to this page--%>
	<c:import url="Footer.jsp"></c:import>
</body>
</html>