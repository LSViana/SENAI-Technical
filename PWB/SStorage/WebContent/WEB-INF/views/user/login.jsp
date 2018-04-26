<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/user/api/login" var="loginApi" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title} | Welcome</title>
<%@ include file="/WEB-INF/views/templates/head.jsp"%>
<style>
form button {
	color: white !important;
}

.demo-layout-transparent {
	background:
		urL("http://www.photos-public-domain.com/wp-content/uploads/2011/12/sky-at-sunrise.jpg")
		center/cover;
	filter: saturate(.5);
}

.demo-layout-transparent .mdl-layout__header, .demo-layout-transparent .mdl-layout__drawer-button,
	.demo-layout-transparent .mdl-navigation__link {
	color: white;
}

.demo-card-wide.mdl-card {
	width: 512px;
}

.demo-card-wide>.mdl-card__title {
	color: #fff;
	background:
		url('https://followthecolours.com.br/wp-content/uploads/2012/09/Point-Dume-Malibu-CA-Sunrise-546am.jpg')
		center/cover;
}

.demo-card-wide>.mdl-card__menu {
	color: #fff;
}

.mdl-card__actions.mdl-card--border {
	display: flex;
	flex-flow: row;
	flex-direction: row-reverse;
}

.mdl-card__title {
	display: flex;
	flex-flow: column;
	background-color: #ff5623bf !important;
	margin: 10px;
	border-radius: 2px;
	background-color: #ff5623bf !important;
}

.mdl-textfield__label {
	color: white !important;
}

.mdl-textfield__input {
	border-bottom: white solid 1px !important;
}

.mdl-textfield {
	width: 300px;
}

.form-button-group {
	display: flex;
	flex-flow : row-reverse;
	width: 300px;
	flex-flow: row-reverse;
}

.form-button-group button {
	font-weight: 600;
}

.form-button-group button:hover {
	background-color: #ff6d41;
}

.form-button-group button:active {
	background-color: #ff5623bf;
}

h2.form-title {
    margin: 10px 10px 0px 10px;
    font-size: xx-large;
    font-family: Roboto;
    background: linear-gradient(to top, #a8606f 0%, blue 100px);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
}

#login-section {
	padding: 20px;
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/views/templates/header.jsp"%>
	</header>
	<main>
	<section id="login-section">
		<f:form method="post" action="${loginApi}" modelAttribute="user"  >
			<div class="demo-card-wide mdl-card mdl-shadow--2dp">
				<h2 class="form-title">Welcome to ${title}</h2>
				<div class="mdl-card__title">
					<div class="mdl-textfield mdl-js-textfield">
						<f:input path="email" cssClass="mdl-textfield__input" type="email" />
<!-- 						<input class="mdl-textfield__input" type="email" id="email" name="email"> -->
						<label class="mdl-textfield__label" for="email">Email</label>
					</div>
					<f:errors element="p" path="email" />
					<div class="mdl-textfield mdl-js-textfield">
						<f:password path="password" cssClass="mdl-textfield__input" />
<!-- 						<input class="mdl-textfield__input" type="password" id="password" name="password"> -->
						<label class="mdl-textfield__label" for="password">Password</label>
					</div>
					<f:errors element="p" path="password" />
					<div class="form-button-group">
						<f:button value="Sign In" class="mdl-button mdl-js-button">Sign In</f:button>
<!-- 						<button class="mdl-button mdl-js-button">Sign In</button> -->
					</div>
				</div>
			</div>
		</f:form>
	</section>
	</main>
	<footer>
		<%@ include file="/WEB-INF/views/templates/footer.jsp"%>
	</footer>
</body>
<script>
	var btnSignIn = document.getElementById("sign-in-button");
	if(btnSignIn)
		btnSignIn.style.display = "none";
</script>
</html>