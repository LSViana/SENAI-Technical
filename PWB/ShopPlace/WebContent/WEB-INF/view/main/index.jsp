<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="cssRoot" value="/assets/css" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${cssRoot}/main.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
	<p>This page has been opened through Spring Controller.</p>
	<a href="user/sign-up">Sign Up</a>
	<a href="user/sign-in">Sign In</a>
</body>
</html>