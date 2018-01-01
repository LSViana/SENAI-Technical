var tlLoader = document.getElementById("tl-loader");
var dateText = document.querySelector("#timeline h2");
var notes = document.querySelectorAll("#tasks ul li");
var doneTicks = document.querySelectorAll("#tasks ul li div");
var now = new Date();
var startDate = new Date("2017-12-15");
var endDate = new Date("2018-01-01");
var days = new Date((endDate - startDate)).getTime() / (1000 * 60 * 60 * 24);
var spentDays = new Date((endDate - now)).getTime() / (1000 * 60 * 60 * 24);
var perc = Math.floor((days - spentDays) / days * 100);
// Loading month's bar and date label
var i = 0;
var interval = 16;
var originalText = now.toLocaleDateString();
var dateWidth = dateText.clientWidth;
var intervalId = setInterval(animateTimeline, interval);

function animateTimeline() {
    if (i > 100)
        clearInterval(intervalId);
    tlLoader.style.width = "calc(" + i + "% - 16px)";
    dateText.style.paddingLeft = "calc(-" + (dateWidth / 2) + "px + " + i + "% * 0.8)";
    dateText.style.opacity = i / perc;
    Array.prototype.forEach.call(doneTicks, function (el) {
        el.style.opacity = i / perc;
    });
    i += Math.sin(1 - i / perc);
    var textPerc = Math.ceil(i / perc * originalText.length);
    var subText = originalText.substr(0, textPerc);
    dateText.textContent = subText;
};
// Loading achievements
for (var i = 0; i < notes.length; i++) {
    var note = notes[i];
    var currDate = new Date(startDate.valueOf());
    currDate.setDate(startDate.getDate() + i);
    if (currDate.getTime() <= now.getTime()) {
        var d = note.children.item(0);
        d.classList.add("done");
    }
}