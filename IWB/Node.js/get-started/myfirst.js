var http = require("http");
var dt = require("./myfirstmodule");

var server = http.createServer(function(req, res) {
    res.writeHead(200, {'Content-Type': 'text/html'});
    res.write("The date and time are currently: " + dt.myDateTime());
    res.end(" || Greetings, Universe!");
});

server.listen(8080);