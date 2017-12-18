var intId = setInterval(function () {
    var docState = document.readyState;
    if (docState != "complete") {
        return;
    }
    var tasks = document.getElementById("tasks");
    var timeLine = document.getElementById("timeline");
    var tlLoader = document.getElementById("tl-loader");
    var dateText = document.querySelector("#timeline h2");
    var notes = document.querySelectorAll("#tasks ul li");
    var doneTicks = document.querySelectorAll("#tasks ul li div");
    var now = new Date();
    var startDate = new Date("2017-12-15 00:00");
    var endDate = new Date("2018-01-01 00:00");
    var days = new Date((endDate - startDate)).getTime() / (1000 * 60 * 60 * 24);
    var spentDays = new Date((endDate - now)).getTime() / (1000 * 60 * 60 * 24);
    var perc = Math.floor((days - spentDays) / days * 100);
    // Loading some themes
    var textDate = now.toLocaleDateString();
    if (textDate.indexOf("2017") != -1) {
        if (textDate.indexOf("12") == 0) {
            if (textDate.indexOf("25") == 3) {
                // Christmas Theme
                timeLine.classList.add("christmas-header");
                tasks.classList.add("christas-main");
            } else if (textDate.indexOf("18") == 3) {
                // Disney Theme
                timeLine.classList.add("disney-header");
                tasks.classList.add("disney-main");
            }
        }
    } else if (textDate.indexOf("2018") != -1) {
        if (textDate.indexOf("1") == 0) {
            if (textDate.indexOf("1") == 2) {
                // New Year
                timeLine.classList.add("new-year-header");
                tasks.classList.add("new-year-main");
            }
        }
    }
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
        var currDate = new Date(startDate);
        currDate.setDate(currDate.getDate() + i);
        currDate = new Date(currDate.toDateString());
        if (currDate <= now) {
            var d = note.children.item(0);
            d.classList.add("done");
        }
    }
    clearInterval(intId);
}, 50);