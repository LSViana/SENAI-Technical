<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/sign-up" var="sign_up" />
<c:url value="/sign-in" var="sign_in" />
<c:url value="/profile" var="profile" />
<c:url value="/app/games" var="games" />
<c:url value="/app/animes" var="animes" />
<c:url value="/logout" var="logout" />
<c:url value="/head" var="head" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TecNow - Home</title>
</head>
<body>
	<c:import url="/head"></c:import>
	<div>
		<c:choose>
			<c:when test="${user != null}">
			<!-- When the User is logged in -->
				<div>
					<p>Welcome, ${user.name}!</p>
				</div>
				<div>
					<p>
						<a href="${logout}">
							Sign Out
						</a>
					<p>
					<p>
						<a href="${profile}">
							Profile
						</a>
					</p>
					<p>
						<a href="${games}">
							Games
						</a>
					</p>
					<p>
						<a href="${animes}">
							Animes
						</a>
					</p>
				</div>
			</c:when>
			<c:otherwise>
			<!-- Receiving a Guest -->
				<div>
					<p>
						<a href="${sign_in}">
							Sign In
						</a>
					</p>
					<p>
						<a href="${sign_up}">
							Sign Up
						</a>
					</p>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>