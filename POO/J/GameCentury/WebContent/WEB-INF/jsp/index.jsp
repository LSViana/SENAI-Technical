<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome to GameCentury! | Home</title>
	<link rel="stylesheet" href="css/common.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@ include file="common/header.jsp" %>
	<main>
		<section id="main-offer">
			<div>
				<!--<img src="img/battlefield-base.jpg" alt="Battefield 3 Scene">-->
				<img src="img/battlefield-chars.png" alt="Battefield 3 Characters">
				<img id="bf3-premium-logo" src="img/bf3-premium-logo.png" alt="Battlefield 3 Premium Logo">
			</div>
			<div>
				<h1>O calor da batalha está de volta!</h1>
				<p>Traga suas armas para o campo de batalha e mostre sua letalidade!</p>
			</div>
		</section>
		<section>
			Estranha
		</section>
	</main>
	<%@ include file="common/footer.jsp" %>
</body>
<script>
	var mainOfferBg = document.getElementById("main-offer");
	var logoPremium = document.getElementById("bf3-premium-logo");
	mainOfferBg.addEventListener("mousemove", function() {
		var e = window.event;
		// Needed information
		var width = mainOfferBg.clientWidth;
		var height = mainOfferBg.clientHeight;
		var midWidth = width / 2;
		var midHeight = height / 2;
	    var posX = e.clientX;
	    var posY = e.clientY;
	    // Offset mechanism
	    var offsetY = -((posY - midHeight) / midHeight) * midHeight / 100;
	    var offsetX = -((posX - midWidth) / midWidth) * midWidth / 100;
	    //
	    var total = Math.abs(offsetX) + Math.abs(offsetY);
	    var blurRate = (total / 3);
	    logoPremium.style.transform = "translate(" + offsetX + "px, " + offsetY + "px)";
	    logoPremium.style.filter = "blur(" + blurRate + "px) saturate(1.75) hue-rotate(5deg) brightness(1.1)";
	});
</script>
</html>