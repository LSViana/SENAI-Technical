// Creating background animation
var mainOfferBg = document.getElementById("home");
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
// Smooth scroll
var menuOptions = document.querySelectorAll(".menu ul a");
Array.prototype.forEach.call(menuOptions, function(a) {
	a.addEventListener("click", function(me) {
		var target = document.getElementById(this.getAttribute("href-smooth").substr(1));
		target.scrollIntoView({ behavior: 'smooth' });
	});
});