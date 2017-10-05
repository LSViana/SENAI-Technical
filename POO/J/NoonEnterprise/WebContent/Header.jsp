<%@page import="java.time.LocalDate"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<style>
nav {
	display: flex;
	justify-content: center;
}

nav img {
	width: 200px;
	height: 60px;
}

p {
	margin: 0px;
}

#dateTime {
	display: flex;
}

#dateTime * {
	font-weight: 600;
	font-style: italic;
	color: gray;
}

#schoolLogo {
	display: flex;
	align-items: center;
}

#separator {
	margin: 0px 15px;
	width: 2px;
	background-color: black;
}
</style>
<nav>
	<div id="welcomeInfo">
		<h3>Welcome to our Agenda System.</h3>
		<div id="dateTime">
			<p>
				Date:
				<%=LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))%>
				- Hour:
				<%=LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))%></p>
		</div>
	</div>
	<div id="separator"></div>
	<div id="schoolLogo">
		<img src="img/logoSENAI.jpg" alt="Logomarca do SENAI">
	</div>
</nav>
<hr>