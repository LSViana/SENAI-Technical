<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mercado Aberto | Login</title>
</head>
<body>
	<form method="post" action="/MercadoAberto/user/register">
		<label for="username">
			Usuário:
			<input type="text" name="username" maxlength="32" />
		</label>
		<label for="password">
			Senha:
			<input type="password" name="password" maxlength="32" />
		</label>
		<input type="submit" value="Entrar" />
	</form>
</body>
</html>
