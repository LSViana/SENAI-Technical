<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/adm/environment/new" var="registerObject" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title} | Welcome</title>
<%@ include file="/WEB-INF/views/templates/head.jsp"%>
<style>
form button {
	font-weight: 600 !important;
    border: 1px solid #ff5722 !important;
}

h2.form-title {
    margin: 10px 10px 0px 10px;
    font-size: xx-large;
    font-family: Roboto;
    background: linear-gradient(to top, #ff5722 0%, #af0051 100px);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
}

.demo-layout-transparent .mdl-layout__header, .demo-layout-transparent .mdl-layout__drawer-button,
	.demo-layout-transparent .mdl-navigation__link {
	color: white;
}

.object-form {
    padding: 10px;
    display: flex;
    justify-content: center;
}

form#environment {
    padding: 12px;
    display: flex;
    flex-flow: column;
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
	<div class="object-form">
		<f:form modelAttribute="environment" action="${registerObject}" method="post" cssClass="mdl-shadow--2dp">
			<h2 class="form-title">Create New User</h2>
			<div class="mdl-textfield mdl-js-textfield">
				<f:input path="name" cssClass="mdl-textfield__input" />
				<label class="mdl-textfield__label" for="firstName">Name</label>
			</div>
			<f:button class="mdl-button mdl-js-button mdl-button--primary mdl-js-ripple-effect">Create Environment</f:button>
		</f:form>
	</div>
	</main>
	<footer>
		<%@ include file="/WEB-INF/views/templates/footer.jsp"%>
	</footer>
</body>
</html>