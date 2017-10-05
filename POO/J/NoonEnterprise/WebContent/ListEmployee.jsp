<%@page import="br.sp.senai.informatica.noon.model.Employee"%>
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
	<c:import url="Noon-Header.jsp"></c:import>
	<%--Create a new instance of AgendaDAO--%>
	<jsp:useBean id="dao" class="br.sp.senai.informatica.noon.data.EmployeeDAO" />
	<%--Create a new DAO to register the items at database--%>
	<table>
		<tr>
			<th>Name</th>
			<th>E-mail</th>
			<th>CPF</th>
			<th>Password</th>
		</tr>
		<c:forEach var="employee" items="${ dao.all }" varStatus="id">
			<tr style="background-color : #${ id.count % 2 == 0 ? 'EEEEEE' : 'CCCCCC' }">
				<td>${ employee.name }</td>
				<td>
					<c:if test="${ not empty employee.email }">
						<a href="mailto:${ employee.email }">${ employee.email }</a>
					</c:if>
					<c:if test="${ empty employee.email }">
						E-mail not supplied
					</c:if>
				</td>
				<td>${ employee.CPF }</td>
				<td>${ employee.password }</td>
			</tr>
		</c:forEach>
	</table>
	<%--Importing footer to this page--%>
	<c:import url="Noon-Footer.jsp"></c:import>
</body>
</html>