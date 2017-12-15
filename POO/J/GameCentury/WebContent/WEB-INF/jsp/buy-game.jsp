<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameCentury | Buy your Game</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@ include file="common/header-min.jsp"%>
	<div id="main-message">
		<p>Game codename:</p>
		<h2>
			<%
				String gameName = request.getParameter("gameName");
				out.print(gameName);
			%>
		</h2>
		<div>
			<img
				src="<%String imgSrc = "https://d30y9cdsu7xlg0.cloudfront.net/png/45447-200.png";
			switch (gameName) {
			case "bf3-premium":
				imgSrc = "https://originassets.akamaized.net/origin-com-store-final-assets-prod/50182/PACKART_PRESET_LARGE_JPG_231x326/71677_LB_231_%5E_2012-12-11-14-12-05_36201e7a1f6556cc8a98bde9373692b8b674cb8df49ae0e76b9fc99a4845426b.jpg";
				break;
			case "cod-mw3":
				imgSrc = "https://upload.wikimedia.org/wikipedia/en/b/bf/Call_of_Duty_Modern_Warfare_3_box_art.png";
				break;
			case "bioshock-infinite":
				imgSrc = "https://upload.wikimedia.org/wikipedia/pt/a/a3/Official_cover_art_for_Bioshock_Infinite.jpg";
				break;
			case "gta-v":
				imgSrc = "https://www.rockstargames.com/V/img/global/order/GTAV-PC.jpg";
				break;
			case "bfh":
				imgSrc = "https://1stpal.com/Files/uploads/2016/04/battlefield-hardline-cd-key.jpg";
				break;
			case "nfs-mw":
				imgSrc = "https://upload.wikimedia.org/wikipedia/en/9/91/Need_for_Speed%2C_Most_Wanted_2012_video_game_Box_Art.jpg";
				break;
			case "pu-battlegrounds":
				imgSrc = "https://www.fullrecoil.com.br/wp-content/uploads/2017/06/880985-1.jpg";
				break;
			}
			out.print(imgSrc);%>"
				alt="Game cover" />
		</div>
	</div>
	<form method="post"
		action="redirect?page=index.jsp&gameName=<%out.print(request.getAttribute("gameName"));%>&buy=true">
		<div>
			<label for="username">Type your username to confirm:</label>
			<input type="text" name="username"></input>
		</div>
		<button id="buy-button">Get My Reserve</button>
	</form>
	<%@ include file="common/footer.jsp"%>
</body>
</html>