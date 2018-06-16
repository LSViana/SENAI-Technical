<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/" var="raiz" />
<c:url value="/usuario/autenticar" var="urlAutenticarUsuario" />

<!DOCTYPE html>
<html>
<head>
	<c:import url="templates/head.jsp"/>
	
	<style>
	body{
		background-image: linear-gradient(to left bottom, #2432cc, #cc29cc, #cc2525);
	}
	
	form *{
		display: block;
	}
	
	form label input{
		background-color: transparent;
		border-color: white;
		border-style: solid;]
		color: white;
		font-size: 24px;
		width: 100%;
	}
	
	form > *{
		margin-bottom: 16px;
	}
	
	form button.btn{
		background-color: transparent;
		border-color: white;
		color: white;
		margin: auto;
		
		transition: background-color .5s, border-color 1s, color .5s;
	}
	
	form button.btn:hover{
		background-color: white;
		border-color: black;
		color: black;
	}
	</style>
</head>
<body class="d-flex aln-items-center">
	<div class="aln-items-center container d-flex" style="height: 500px;">
		<div>
			<img alt="juca control logo" src="${raiz}assets/images/jucacontrol_logo.png">
		</div>
		<div>
			<form action="${urlAutenticarUsuario}" method="post" style="color: white">
				<label>
					E-mail
					<input name="email" type="email" required="required" maxlength="120" id="inputEmail" cssStyle="color: white;"/>
				</label>
				<label>
					Senha
					<input name="senha" type="password" required="required" maxlength="20" cssStyle="color: white;" />
				</label>
				<button class="btn" type="submit">ENTRAR</button>
			</form>
		</div>	
	</div>
</body>
</html>