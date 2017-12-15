<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>GameCentury | Home</title>
	<link rel="stylesheet" href="css/common.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@ include file="common/header.jsp" %>
	<main>
		<section id="home">
			<div>
				<img id="bf3-premium-logo" src="img/bf3-premium-logo.png" alt="Battlefield 3 Premium Logo">
				<img src="img/battlefield-chars.png" alt="Battefield 3 Characters">
				<img src="img/battlefield-base.jpg" alt="Battefield 3 Scene">
				<a href="redirect?page=buy-game&gameName=bf3-premium">
					<button id="button-buy" >GET MY RESERVE</button>
				</a>
			</div>
			<div>
				<h1>Battle's heat is coming back!</h1>
				<p>Bring your weapons to ground and air and be <span>lethal</span>!</p>
			</div>
		</section>
		<div class="section-divider"></div>
		<section id="games">
			<div class="top-games-selector land-message">
				<h2>Take a look at our options:</h2>
				<ul>
					<a href="redirect?page=buy-game&gameName=cod-mw3">
						<li>Call of Duty: MW3</li>
					</a>
					<a href="redirect?page=buy-game&gameName=bioshock-infinite">
						<li>Bioshock: Infinite</li>
					</a>
					<a href="redirect?page=buy-game&gameName=gta-v">
						<li>Grand Theft Auto: V</li>
					</a>
					<a href="redirect?page=buy-game&gameName=bfh">
						<li>Battlefield: Hardline</li>
					</a>
					<a href="redirect?page=buy-game&gameName=nfs-mw">
						<li>Need for Speed: Most Wanted</li>
					</a>
					<a href="redirect?page=buy-game&gameName=pu-battlegrounds">
						<li>Playerunknown's Battlegrounds</li>
					</a>
				</ul>
			</div>
		</section>
		<div class="section-divider"></div>
		<section id="about-us">
			<div class="land-message">
				<h2>About Us</h2>
				<p>The Company <span>GameCentury</span> was born at year 2000, when the new Century brought us new things to discover at many technology's areas, like mobile connections and digital games, we started our works being an intermediary at the sell operations of just opened game makers enterprises, but we switched our main operation from marketing area to the main seller of the greatest titles of nowadays, acting with <span>Rockstar Games</span>, <span>EA Games</span> and many others!</p>
			</div>
		</section>
		<div class="section-divider"></div>
		<section id="find-us">
			<div class="land-message">
				<h2>Find Us</h2>
				<p>Our E-mail: <span>contact@gamecentury.play</span></p>
				<p>Our Telephone: <span>800-698-4637</span></p>
			</div>
		</section>
	</main>
	<%@ include file="common/footer.jsp" %>
</body>
<script src="./js/jquery-3.2.1.min.js"></script>
<script src="./js/main.js"></script>
</html>