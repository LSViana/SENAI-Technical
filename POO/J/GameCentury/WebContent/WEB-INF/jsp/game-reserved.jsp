<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameCentury | Buy your Game</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@ include file="common/header-min.jsp"%>
	<div id="main-message">
		<h2>Your game has been successfully reserved!</h2>
		<p>
		We hope you to enjoy: <span><% out.print(request.getParameter("gameName")); %></span>
		</p>
	</div>
	<%@ include file="common/footer.jsp"%>
</body>
</html>