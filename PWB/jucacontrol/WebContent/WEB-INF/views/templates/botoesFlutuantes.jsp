<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="raiz" />

<c:url value="/assets" var="assets" />
<c:url value="/app/" var="urlVisualizarOcorrencias" />
<c:url value="/app/adm/categoria" var="urlVisualizarCategorias" />
<c:url value="/app/adm/usuario" var="urlVisualizarUsuarios" />

<%--Botões de gerenciamento --%>
<div class="float-btn-group">
	<c:if test="${usuarioLogado.tipo eq 'ADMINISTRADOR'}">
		<a title="Usuários do sistema" class="btn" href="${urlVisualizarUsuarios}">
			<img alt="Usuários do sistema" src="${assets}/images/user.png">
		</a>
		<a title="Categorias de ocorrências" class="btn" href="${urlVisualizarCategorias}">
			<img alt="Categorias de ocorrências" src="${assets}/images/label.png">
		</a>
	</c:if>
	<a title="Visualizar ocorrências" class="btn" href="${urlVisualizarOcorrencias}">
		<img alt="Abrir nova ocorrência" src="${assets}/images/ocorrencia.png">
	</a>
</div>