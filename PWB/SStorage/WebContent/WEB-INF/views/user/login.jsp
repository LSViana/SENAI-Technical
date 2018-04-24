<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title} | Welcome</title>
<%@ include file="/WEB-INF/views/templates/head.jsp"%>
<style>
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
</style>
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/views/templates/header.jsp"%>
	</header>
	<main>
	
	</main>
	<footer>
		<%@ include file="/WEB-INF/views/templates/footer.jsp"%>
	</footer>
</body>
</html>