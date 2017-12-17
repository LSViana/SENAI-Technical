var http = require("http");
var fs = require("fs");
var url = require("url");

var server = http.createServer(function(req, res) {
    // Getting requested page
    var query = url.parse(req.url);
    var fileName = "./" + query.pathname;
    // Opening file
    var file = fs.readFile(fileName, function(err, data) {
        res.writeHead(200, {"Content-Type": "text/html"});
        // Response part
        if(err) {
            res.write("Page not found!");
        }
        else {
            res.write(data);
        }
        return res.end();
    });
});

server.listen(8080);