var inputs = document.getElementsByTagName("input");
var firstPhrase = document.getElementById("first-phrase");
var mainWord = document.getElementById("main-word");
for (var index = 0; index < inputs.length; index++) {
    var element = inputs[index];
    if (index == inputs.length - 1) {
        element.addEventListener("input", function(a) {
            var word = inputs[0].value + inputs[1].value + inputs[2].value + inputs[3].value;
            if(word == "like") {
                firstPhrase.classList.add("quick-exit-right");
                Array.prototype.forEach.call(inputs, function(value, index, listObj) {
                    value.classList.add("remove-borders");
                });
            }
        });
    }
    element.addEventListener("input", function (a) {
        var baseId = this.id.substr(0, this.id.length - 1);
        var nextId = baseId + (Number(this.id.substring(this.id.length - 1)) + 1);
        var previousId = baseId + (Number(this.id.substring(this.id.length - 1)) - 1);
        var nextElement;
        if (a.data == null) {
            nextElement = document.getElementById(previousId);
        } else {
            nextElement = document.getElementById(nextId);
        }
        if(nextElement != undefined)
            nextElement.focus();
    });
}