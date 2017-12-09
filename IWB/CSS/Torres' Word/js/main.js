var inputs = document.getElementsByTagName("input");
var firstPhrase = document.getElementById("first-phrase");
var secondPhrase = document.getElementById("second-phrase");
var mainWord = document.getElementById("main-word");
// Dynamic
function initializeInput(input) {
    // Typing
    var letterPosition = Number(input.id.substr(6));
    input.addEventListener("keydown", function(event) {
        if(event.code == "Backspace") {
            if(inputs.length != 1) {
                this.remove();
                var previous = document.getElementById("letter" + (letterPosition - 1));
                previous.focus();
                window.getSelection().removeAllRanges();
            }
            else {
                this.value = null;
                return;
            }
        }
        else if(this.value.length > 0) {
            if(inputs.length >= 10 || event.key.length > 1) {
                return;
            }
            var freshInput = document.createElement("input");
            mainWord.appendChild(freshInput);
            freshInput.setAttribute("maxlength", "1");
            freshInput.setAttribute("id", "letter" + (letterPosition + 1));
                freshInput.value = event.key;
            initializeInput(freshInput);
            freshInput.focus();
        }
        inputs = document.getElementsByTagName("input");
        var word = "";
        Array.prototype.forEach.call(inputs, function(a, b, c) {
            word += a.value;
        });
        word = word.toLowerCase().trim();
        if(word == "love") {
            console.log(1);
            $("#main-box img").css({
                "display": "unset"
            })
            .animate({
                "opacity": "1"
            });
            $("#main-box *").removeClass("make-pinker");
            $("#main-box *").addClass("make-golder");
        }
        else if(word == "like") {
            console.log(2);
            $("#main-box p").slideUp(1000);
            $("#upper-phrase, #lower-phrase").animate({
                "opacity": "1"
            });
        }
        else {
            console.log(3);
            $("#main-box img").css({
                "display": "none"
            })
            .animate({
                "opacity": "0"
            });
            $("#upper-phrase, #lower-phrase").animate({
                "opacity": "0"
            });
            $("#main-box *").removeClass("make-golder");
            $("#main-box *").addClass("make-pinker");
            $("#main-box *").show();
        }
    });
}
Array.prototype.forEach.call(inputs, function(a, b, c) {
    initializeInput(a);
});