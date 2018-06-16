<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
		<form:form modelAttribute="ocorrencia" action="${urlSalvarOcorrencia}" method="post" class="grid-flex">

			<form:hidden path="id"/>

			<div class="row">
				<div class="col flex-1">
					<form:input path="titulo" placeholder="Insira o título da ocorrência"/>	
				</div>
			</div>
			<div class="row">
				<div class="col flex-1">
					<form:textarea path="descricao"/>
				</div>
			</div>
			<div class="row">
				<div class="col flex-1">
					<%--Criando o select com spring/form --%>
					<form:select path="categoria.id" items="${categorias}"
					itemValue="id" itemLabel="nome"></form:select>
				</div>
			</div>
			<div class="row btn-group">
				<%-- Verifica se não é uma alteração e, caso seja, verifica se o usuário é dono --%>
				<c:choose>
					<c:when test="${empty ocorrencia.id  or ocorrencia.emissor.id eq usuarioAutenticado.id}">
						<button type="submit" class="btn btn-blue col flex-1">SALVAR</button>
					</c:when>
					<c:otherwise>
						<c:url value="/app" var="raizApp" />
						<a href="${raizApp}" id="dummy">Clique aqui para voltar para o menu principal</a>
						
						<style>
							@keyframes fx-rotate-and-scale{
								0%{font-size: 12px;
								transform : rotate(0deg)}
								100%{font-size: 48px;
								transform : rotate(360deg)
								}
							}
							
							#dummy	{
							
								animation : fx-rotate-and-scale 1s infinite;
							}
						</style>
						
					</c:otherwise>
				</c:choose>
			</div>
		</form:form>
	</main>
	<c:import url="../templates/botoesFlutuantes.jsp" />
</body>
</html>