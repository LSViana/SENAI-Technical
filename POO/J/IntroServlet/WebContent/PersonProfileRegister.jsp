<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Person Profile Register</title>
	<style>
	form > * {
		display: block;
		margin: 2.5px;
	}
	</style>
</head>
<body>
	<form method="post" action="/IntroServlet/register">
		<input type="text" name="firstname">
		<input type="text" name="lastname">
		<label>
			<input type="radio" name="gender" value="Female">
			Female
		</label>
		<label>
			<input type="radio" name="gender" value="Male">
			Male
		</label>
		<label>
			<input type="checkbox" name="smoker" value="smoker">
			Smoker
		</label>
		<input type="submit" value="Send">
	</form>
</body>
</html>