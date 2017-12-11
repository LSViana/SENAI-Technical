console.log("Thaytle");
var $body = $("body");
console.log($body);
$body.on("click", function(a, b) {
    $(this).css({
        "background-color": "red"
    });
});