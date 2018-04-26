<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/user/login" var="openLogin" />
<c:url value="/" var="main" />
<style>
@import url('https://fonts.googleapis.com/css?family=Roboto:400,700');

a {
	text-decoration: none !important;
}

.mdl-layout__header-row {
	padding: 0px 40px;
}

.mdl-button--raised.mdl-button--colored {
	background-color: rgb(255, 61, 0) !important;
}
b
.custom-white-bold-text {
	color: white !important;
	font-weight: 600;
}

.custom-topnav a:first-child, .custom-topnav a:first-child {
	padding: 0px !important;
}

.custom-topnav a:not (:first-child ) {
	padding: 0px 0px 0px 24px;
}
</style>
<div class="mdl-layout__header-row mdl-layout__header is-casting-shadow">
	<!-- Title -->
	<a href="${main}"><span class="mdl-layout-title"> <span
			class="custom-white-bold-text">${title}</span> | Start Page
	</span></a>
	<div class="mdl-layout-spacer"></div>
	<!-- Navigation -->
	<nav class="mdl-navigation custom-topnav">
		<a class="mdl-navigation__link" href="${openLogin}">
			<button
				class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-js-ripple-effect">Sign
				In</button>
		</a>
	</nav>
</div>