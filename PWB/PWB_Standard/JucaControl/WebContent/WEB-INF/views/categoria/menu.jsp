<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app/adm/categoria" var="urlCategorias" />
<c:url value="/app/adm/categoria/deletar" var="urlDeletarCategoria" />
<c:url value="/app/adm/categoria/salvar" var="urlSalvarCategoria" />

<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"/>
</head>
<body>
	<c:import url="../templates/header.jsp"/>
	<main class="container">
		<h1>Categorias de Ocorrência</h1>
		<section id="sectionCategorias" class="container">
		<div class="flex-grid">
			<div class="row">
				<div class="col flex-1">
					<c:choose>
						<c:when test="${empty categoria.id}">
							<h2>Nova Categoria</h2>
						</c:when>
						<c:otherwise>
							<h2>Editando categoria: ${categoria.nome}</h2>
						</c:otherwise>
					</c:choose>
					<form:form modelAttribute="categoriaOcorrencia" action="${urlSalvarCategoria}" method="post">
						<form:hidden path="id" />
						<div class="flex-grid">
							<div class="row">
								<div class="col flex-1">
									<label>
										Nome
										<form:input path="nome"/>
										<form:errors path="nome" element="div" cssClass="error" />
									</label>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<button type="submit" class="btn btn-blue">SALVAR</button>
								</div>
								<c:if test="${not empty categoriaOcorrencia.id}">
									<div class="col">
										<a class="btn" href="${urlCategorias}">NOVO</a>
									</div>
									<div class="col">
										<a class="btn btn-red" href="${urlDeletarCategoria}?id=${categoriaOcorrencia.id}">DELETAR</a>
									</div>			
								</c:if>
							</div>
						</div>
					</form:form>
				</div>
				<div class="col flex-1">
					<h2>Categorias Cadastradas</h2>
					<%-- Tabela de ocorrências --%>
					<table id="tabelaOcorrencias" class="table container read-container ma-t-l">
						<thead>
							<tr>
								<th>Categorias</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${categorias}" var="categoria">
								<tr>
									<td>
										<a href="${urlCategorias}?id=${categoria.id}">${categoria.nome}</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		</section>
	</main>
	<c:import url="../templates/botoesFlutuantes.jsp" />
</body>
</html>