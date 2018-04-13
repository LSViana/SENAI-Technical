<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app" var="urlOcorrencias" />
<c:url value="/app/ocorrencia" var="urlVisualizarOcorrencia" />
<c:url value="/app/ocorrencia/nova" var="urlNovaOcorrencia" />
<c:url value="/app/ocorrencia/assumir" var="urlAssumirOcorrencia" />
<c:url value="/app/ocorrencia/encerrar" var="urlEncerrarOcorrencia" />


<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"/>
	<style>
	#tabelaOcorrencias img{
		background-image: linear-gradient(to left bottom, #2432cc, #cc29cc, #cc2525);
		width: 100px;
	}
	</style>
</head>
<body>
	<c:import url="../templates/header.jsp"/>
	<main class="container">
		<a href="${urlNovaOcorrencia}" class="btn btn-red d-block ma-l-auto ma-t-s" style="max-width: 220px"> Abrir ocorrência</a>
		<h1 class="fx-slide-in">Ocorrências</h1>
		<section id="sectionOcorrencias">
			<h2>Classificar por: </h2>
			<%--Filtros de busca --%>
			<form action="${urlOcorrencias}" method="get" class="flex-grid ma-b-l" style="max-width: 400px;">
				<div class="row">
				<div class="col flex-2">
					<select name="pesquisa">
						<c:forEach items="${pesquisas}" var="pesquisa">
							<option value="${pesquisa}">${pesquisa.descricao}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col flex-1">
					<button class="btn btn-blue" type="submit">Pesquisar</button>
				</div>
				</div>
			</form>
			
			<%-- Tabela de ocorrências --%>
			<table id="tabelaOcorrencias" class="table container read-container">
				<thead>
					<tr>
						<th>#</th>
						<th>Ocorrências</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ocorrencias}" var="ocorrencia">
						<tr>
							<%--- Sinalizador de status --%>
							<td></td>
							<%-- Descrição da ocorrência --%>
							<td>
								<p class="ocorrencia-id">
									<a href="${urlVisualizarOcorrencia}/editar?id=${ocorrencia.id}">
										${ocorrencia.id}
									</a>
								</p>
								<h4>${ocorrencia.titulo}</h4>
								<p class="ocorrencia-detalhe"><b class="color-pink">Data de abertura: </b>
									${ocorrencia.dataCadastro}
								</p>
								<p class="ocorrencia-detalhe"><b class="color-pink">Última modificação: </b>
									${ocorrencia.dataModificacao}
								</p>
								<p class="ocorrencia-detalhe"><b class="color-pink">Data de conclusão: </b>
									${ocorrencia.dataConclusao}
								</p>
							</td>
							<%--Quem atendeu ocorrencia/link de atendimento--%>
							<td>
								<c:choose>
									<c:when test="${empty ocorrencia.tecnico}">
										<a href="${urlAssumirOcorrencia}?id=${ocorrencia.id}">Assumir</a>
									</c:when>
									<c:otherwise>
									
										<img alt="Imagem do técnico" title="Atendida por ${ocorrencia.tecnico.nome} ${ocorrencia.tecnico.sobrenome}"
										src="${ocorrencia.tecnico.caminhoFoto}">
										
										
										<c:choose>
											<c:when test="${not empty ocorrencia.dataConclusao}">
												Encerrado
											</c:when>
											<c:when test="${usuarioLogado.tipo eq 'ADMINISTRADOR' or 
															ocorrencia.tecnico.id eq usuarioLogado.id }">
												<a href="${urlEncerrarOcorrencia}?id=${ocorrencia.id}">Encerrar</a>
											</c:when>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
	</main>
	<c:import url="../templates/botoesFlutuantes.jsp"/>
</body>
</html>