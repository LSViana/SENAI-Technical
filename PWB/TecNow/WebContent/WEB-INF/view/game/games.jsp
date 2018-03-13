<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/" var="index" />
<c:url value="/app/games/insert" var="game_insert" />
<c:url value="/app/games/edit" var="game_edit" />
<c:url value="/app/games/delete" var="game_delete" />
<c:url value="/head" var="head" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TecNow - Your Games</title>
</head>
<body>
	<c:import url="/head"></c:import>
	<p>Those are your games, ${user.name}!</p>
	<p>
		<a href="${game_insert}">
			Insert new game
		</a>
	</p>
	<table>
		<thead>
			<tr>
				<th>Id.</th>
				<th>Name</th>
				<th>Category</th>
				<th>Date Register</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${games}" var="game">
			<!-- Creating the Rows at Table with User's Games from Database -->
				<tr>
					<td>${game.id}</td>
					<td>${game.name}</td>
					<td>${game.category}</td>
					<td>${game.dateRegister}</td>
					<td data-type="action">
						<a href="${game_edit}?id=${game.id}">Edit</a>
					</td>
					<td data-type="action">
						<a href="${game_delete}?id=${game.id}">Delete</a>
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