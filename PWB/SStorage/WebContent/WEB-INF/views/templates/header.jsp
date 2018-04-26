<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/user/login" var="openLogin" />
<c:url value="/user/api/logout" var="openLogout" />
<c:url value="/" var="main" />
<c:url value="/assets/header.css" var="headerCss" />
<c:url value="/adm/" var="adminIndex" />
<link rel="stylesheet" href="${headerCss}" />
<div class="mdl-layout__header-row mdl-layout__header is-casting-shadow">
	<!-- Title -->
	<a href="${main}"><span class="mdl-layout-title"> <span
			class="custom-white-bold-text">${title}</span> | Start Page
	</span></a>
	<div class="mdl-layout-spacer"></div>
	<!-- Navigation -->
	<nav class="mdl-navigation custom-topnav">
		<c:choose>
			<c:when test="${user_auth == null}">
				<a class="mdl-navigation__link" href="${openLogin}">
					<button id="sign-in-button"
						class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-js-ripple-effect">Sign
						In</button>
				</a>
			</c:when>
			<c:otherwise>
				<a class="mdl-navigation__link" href="${openLogout}">
					<button id="sign-in-button"
						class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-js-ripple-effect">Sign
						Out</button>
				</a>
				<c:choose>
					<c:when test="${user_auth.type eq 'ADMINISTRATOR'}">
						<a class="mdl-navigation__link" href="${adminIndex}">
							<button id="sign-in-button"
								class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-js-ripple-effect">Manage Application</button>
						</a>
					</c:when>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</nav>
</div>