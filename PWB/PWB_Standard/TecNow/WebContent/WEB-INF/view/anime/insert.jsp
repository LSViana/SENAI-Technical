<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/" var="index" />
<c:url value="/app/animes/persist" var="anime_persist" />
<c:url value="/app/animes/delete" var="anime_delete" />
<c:url value="/head" var="head" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TecNow - Persist Anime</title>
</head>
<body>
	<c:import url="/head"></c:import>
	<p>Persist your anime, ${user.name}!</p>
	<form action="${anime_persist}" method="post">
	<!-- Form to Persist data about Animes -->
		<input style="display: none" type="number" name="id" value="${id != null ? id : -1}">
		<div>
			<label for="name">Name</label>
			<input type="text" name="name" value="${name}">
			<p id="invalid-name">The name must contain between 1 and 40 characters!</p>
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