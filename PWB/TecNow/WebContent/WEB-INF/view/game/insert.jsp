<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/" var="index" />
<c:url value="/app/games/persist" var="game_persist" />
<c:url value="/app/games/delete" var="game_delete" />
<c:url value="/head" var="head" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TecNow - Persist Game</title>
</head>
<body>
	<c:import url="/head"></c:import>
	<p>Persist your game, ${user.name}!</p>
	<form action="${game_persist}" method="post">
	<!-- Form to Persist data about Games -->
		<input style="display: none" type="number" name="id" value="${id != null ? id : -1}">
		<div>
			<label for="name">Name</label>
			<input type="text" name="name" value="${name}">
			<p id="invalid-name">The name must contain between 1 and 40 characters!</p>
		</div>
		<div>
			<label for="category">Category</label>
			<select name="category">
				<option value="SHOOT" ${category == "SHOOT" ? "selected" : ""}>
					Shoot
				</option>
				<option value="RPG" ${category == "RPG" ? "selected" : ""}>
					RPG
				</option>
				<option value="PLATFORM" ${category == "PLATFORM" ? "selected" : ""}>
					Platform
				</option>
				<option value="SPORT" ${category == "SPORT" ? "selected" : ""}>
					Sport
				</option>
				<option value="HACK_AND_SLASH" ${category == "HACK_AND_SLASH" ? "selected" : ""}>
					Hack and Slash
				</option>
				<option value="OTHER" ${category == "OTHER" ? "selected" : ""}>
					Other
				</option>
			</select>
		</div>
		<input type="submit" value="Persist">
	</form>
	<p>
		<a href="${index}">
			Go Home
		</a>
	</p>
</body>
<script>
var url = new URL(window.location.href);
var invalidName = document.getElementById("invalid-name");
var error = url.searchParams.get("message");
invalidName.style.display = error == "name" ? "unset" : "none";
</script>
</html>