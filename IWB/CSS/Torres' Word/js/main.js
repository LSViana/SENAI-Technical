var inputs = document.getElementsByTagName("input");
var firstPhrase = document.getElementById("first-phrase");
var secondPhrase = document.getElementById("second-phrase");
var mainWord = document.getElementById("main-word");
// Dynamic
function initializeInput(input) {
    // Typing
    var letterPosition = Number(input.id.substr(6));
    input.addEventListener("input", function(event) {
        if(event.data == null) {
            if(inputs.length != 1) {
                this.remove();
                var previous = document.getElementById("letter" + (letterPosition - 1));
                window.getSelection().removeAllRanges();
                previous.focus();
                previous.setSelectionRange(1, 1);
            }
            else {
                this.value = null;
                return;
            }
        }
        else if(this.value.length > 1) {
            if(inputs.length >= 10 || event.data.length > 1) {
                this.value = this.value.substr(0, 1);
                return;
            }
            var freshInput = document.createElement("input");
            mainWord.appendChild(freshInput);
            freshInput.setAttribute("id", "letter" + (letterPosition + 1));
            freshInput.setAttribute("type", "text");
            freshInput.setAttribute("autocorrect", "off");
            freshInput.setAttribute("autocapitalize", "none");
            freshInput.value = this.value.substr(1, 1);
            this.value = this.value.substr(0, 1);
            initializeInput(freshInput);
            freshInput.focus();
            freshInput.setSelectionRange(1, 1);
        }
        inputs = document.getElementsByTagName("input");
        var word = "";
        Array.prototype.forEach.call(inputs, function(a, b, c) {
            word += a.value;
        });
        word = word.toLowerCase().trim();
        if(word == "love") {
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
            $("#main-box p")
            .animate({width:'toggle'},350);
            // .slideRight(1000);
            $("#upper-phrase, #lower-phrase").animate({
                "opacity": "1"
            });
        }
        else {
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