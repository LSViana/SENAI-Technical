<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/adm/user/create" var="userCreate" />
<c:url value="/adm/patrimony/create" var="patrimonyCreate" />
<c:url value="/adm/environment/create" var="environmentCreate" />
<c:url value="/adm/user" var="userList" />
<c:url value="/adm/patrimony" var="patrimonyList" />
<c:url value="/adm/environment" var="environmentList" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title} | Welcome</title>
<%@ include file="/WEB-INF/views/templates/head.jsp"%>
<style>
h2.mdl-card__title-text {
	color: #ffd9c2;
	margin-left: 16px;
}

.demo-layout-transparent {
	background:
		urL("http://www.photos-public-domain.com/wp-content/uploads/2011/12/sky-at-sunrise.jpg")
		center/cover;
}

.demo-layout-transparent .mdl-layout__header, .demo-layout-transparent .mdl-layout__drawer-button,
	.demo-layout-transparent .mdl-navigation__link {
	color: white;
}

.demo-card-wide.mdl-card {
	width: 512px;
	flex-basis: 400px;
	margin: 12px;
}

.demo-card-wide>.mdl-card__title {
	position: relative;
	color: #fff;
	height: 176px;
}

.demo-card-wide>.mdl-card__menu {
	color: #fff;
}

.mdl-card__title-text span {
	font-weight: 600 !important;
}

.mdl-card__title-text span {
	margin-left: 5px;
}

.main-cards {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
	flex-wrap: wrap;
	padding: 12px;
}

.mdl-card__title>div:first-child {
	position: absolute;
	width: 100%;
	height: 100%;
	z-index: -1;
	border-radius: 2px;
	left: 8px;
    top: 8px;
}

a.mdl-button.mdl-button--colored.mdl-js-button.mdl-js-ripple-effect {
	border: 1px solid #ff5722 !important;
}

#users-card>div:first-child {
	background: linear-gradient(to right, #ff5722, #af0051);
}

#patrimonies-card>div:first-child {
	background: linear-gradient(to right, #ff5722, #af0051);
}

#environments-card>div:first-child {
	background: linear-gradient(to right, #ff5722, #af0051);
}
</style>
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/views/templates/header.jsp"%>
	</header>
	<main>
	<div class="main-cards">
		<div class="demo-card-wide mdl-card mdl-shadow--2dp">
			<div class="mdl-card__title" id="users-card">
				<div></div>
				<h2 class="mdl-card__title-text">
					Manage
					<span>Users</span>
				</h2>
			</div>
			<div class="mdl-card__supporting-text">Here you can take care
				of users configurations, register, update or even delete them</div>
			<div class="mdl-card__actions mdl-card--border">
				<a
					class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
					href="${userCreate}"> Create</a>
				<a
					class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
					href="${userList}"> List</a>
			</div>
			<div class="mdl-card__menu"></div>
		</div>
		<div class="demo-card-wide mdl-card mdl-shadow--2dp">
			<div class="mdl-card__title" id="patrimonies-card">
				<div></div>
				<h2 class="mdl-card__title-text">
					Manage
					<span>Patrimonies</span>
				</h2>
			</div>
			<div class="mdl-card__supporting-text">Here you can take care
				of patrimony items configurations, register, update or even delete
				them</div>
			<div class="mdl-card__actions mdl-card--border">
				<a
					class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
					href="${patrimonyCreate}"> Create</a>
				<a
					class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
					href="${patrimonyList}"> List</a>
			</div>
			<div class="mdl-card__menu"></div>
		</div>
		<div class="demo-card-wide mdl-card mdl-shadow--2dp">
			<div class="mdl-card__title" id="environments-card">
				<div></div>
				<h2 class="mdl-card__title-text">
					Manage
					<span>Environments</span>
				</h2>
			</div>
			<div class="mdl-card__supporting-text">Here you can take care
				of environment items configurations, register, update or even delete
				them</div>
			<div class="mdl-card__actions mdl-card--border">
				<a
					class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
					href="${environmentCreate}"> Create</a>
				<a
					class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
					href="${environmentList}"> List</a>
			</div>
			<div class="mdl-card__menu"></div>
		</div>
	</div>
	</main>
	<footer>
		<%@ include file="/WEB-INF/views/templates/footer.jsp"%>
	</footer>
</body>
</html>