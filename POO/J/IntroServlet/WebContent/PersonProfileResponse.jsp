<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Person Profile Response</title>
	<style>
	.userdata div > * {
		display: inline;
	}
	</style>
</head>
<body>
	<section class="userdata">
		<div><p>Full Name:</p> <p><% out.write(request.getAttribute("firstname") + " " + request.getAttribute("lastname")); %></p></div>
		<div><p>Gender:</p> <p><% out.write(request.getAttribute("gender").toString()); %></p></div>
		<div><p>Smoker:</p> <input type="checkbox" disabled <% Object smoker = request.getAttribute("smoker"); out.write((smoker != null && smoker.toString().equals("true")) ? "checked" : ""); %>></div>
	</section>
</body>
</html>