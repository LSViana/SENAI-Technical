<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>
</head>
<body>
	<form action="user/register" method="post">
		<label for="name">Name:</label>
		<input type="text" name="name">
		<label for="username">Username:</label>
		<input type="text" name="username">
		<label for="password">Password:</label>
		<input type="password" name="password">
		<input type="submit" value="Sign-In">
	</form>
</body>
</html>