<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app/ocorrencia/deletar" var="urlDeletarOcorrencia" />
<c:url value="/app/ocorrencia/encerrar" var="urlEncerrarOcorrencia" />
<c:url value="/app/ocorrencia/salvar" var="urlSalvarOcorrencia" />


<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"/>
</head>
<body>
	<c:import url="../templates/header.jsp"/>
	<main class="container read-container">
		<h1>Abrir Ocorrência</h1>
		<form action="${urlSalvarOcorrencia}" method="post" class="grid-flex">
			<input type="hidden" name="id"/>
			<div class="row">
				<div class="col flex-1">
					<input type="text" name="titulo" placeholder="Insira o título da ocorrência"/>	
				</div>
			</div>
			<div class="row">
				<div class="col flex-1">
					<textarea name="descricao"></textarea>
				</div>
			</div>
			<div class="row">
				<div class="col flex-1">
					<select name="categoria.id">
						<%--OPTIONS AQUI --%>

					</select>
				</div>
			</div>
			<div class="row btn-group">
				<button type="submit" class="btn btn-blue col flex-1">SALVAR</button>
			</div>
		</form>
	</main>
	<c:import url="../templates/botoesFlutuantes.jsp" />
</body>
</html>