<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/" var="index" />
<c:url value="/app/animes/insert" var="anime_insert" />
<c:url value="/app/animes/edit" var="anime_edit" />
<c:url value="/app/animes/delete" var="anime_delete" />
<c:url value="/head" var="head" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TecNow - Your Animes</title>
</head>
<body>
	<c:import url="/head"></c:import>
	<p>Those are your animes, ${user.name}!</p>
	<p>
		<a href="${anime_insert}">
			Insert new anime
		</a>
	</p>
	<table>
		<thead>
			<tr>
				<th>Id.</th>
				<th>Name</th>
				<th>Date Register</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${animes}" var="anime">
			<!-- Creating the Rows at Table with User's Animes from Database -->
				<tr>
					<td>${anime.id}</td>
					<td>${anime.name}</td>
					<td>${anime.dateRegister}</td>
					<td data-type="action">
						<a href="${anime_edit}?id=${anime.id}">Edit</a>
					</td>
					<td data-type="action">
						<a href="${anime_delete}?id=${anime.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="${index}">
			Go Home
		</a>
	</p>
</body>
</html>