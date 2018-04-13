<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/" var="raiz" />
<c:url value="/app" var="urlHome" />
<c:url value="/sair" var="urlSair" />
<c:url value="/assets" var="assets" />

<style>
	.top-menu-wrapper{
		background-image: linear-gradient(to right, #2432cc, #cc29cc, #cc2525);
		display: block;
		height: 2px;
		width: 100%;
	}
</style>

<header class="top-menu" style="padding: 4px 32px">
	<ul class="top-menu-items no-list" style="flex: 2">
		<li>
			<a href="${urlHome}">
				<img alt="Voltar para início" src="${assets}/images/logo.png" style="width: 24px; height: 24px;" />
			</a>
		</li>
	</ul>
	<ul class="top-menu-items aln-items-center no-list txt-aln-r">
		<li>${usuarioLogado.nome}</li>
		<li><a href="${urlSair}">Sair</a></li>
	</ul>
</header>
<span class="top-menu-wrapper"></span>