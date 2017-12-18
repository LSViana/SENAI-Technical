var http = require("http");
var mysql = require("mysql");
var fs = require("fs");
var url = require("url");
var qs = require("querystring");

// SQL Queries
var getAllCompanies = "SELECT name, description, image FROM `company`";

var con = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "worldskills",
    database: "node-intro"
});

var server = http.createServer(function (req, res) {
    var urlQuery = url.parse(req.url);
    // Answer the request
    var status = 200;
    if (urlQuery.pathname == "/") {
        // To substitute values
        var companiesRows = new String();
        var mainMessage = new String();
        var query = url.parse(req.url);
        if (query.pathname == "/save-company" && req.method == "POST") {
            req.on("data", function (data) {
                // Kill the connection if too much POST data is sent
                if (data.length > 1e6)
                    req.connection.destroy();
                var postData = qs.parse(data.toString());
                con.query(`INSERT INTO \`company\` VALUES(0, '${postData.name}', '${postData.description}', '${postData.image}')`, postData, function (err, results, fields) {
                    if (err) {
                        status = 500;
                        mainMessage = `<div>We couldn't register this company!</div>`;
                    } else {
                        mainMessage = `<div>Company ${postData.name} successfully registered!</div>`;
                    }
                });
            });
            res.writeHead(301, {
                "Location": "/"
            });
            res.end();
        }
        // Get the HTML page from filesystem
        fs.readFile("./index.html", function (err, data) {
            if (err) status = 500;
            // Get the data from database
            con.query(getAllCompanies, function (err, results, fields) {
                if (err) status = 500;
                // Write data to HTML page
                for (var i = 0; i < results.length; i++) {
                    var r = results[i];
                    companiesRows += `<tr><td>${r.name}</td><td>${r.description}</td><td><img src="${r.image}" alt="${r.name} Logo" /></td></tr>`;
                }
                var htmlPage = data.toString().replace("{0}", companiesRows).replace("{1}", mainMessage);
                // Return page to client
                res.writeHead(status, {
                    "Content-Type": "text/html"
                });
                return res.end(htmlPage);
            });
        });
    } else if (urlQuery.pathname.indexOf(".css") != -1) {
        res.writeHead(status, {
            "Content-Type": "text/css"
        });
        fs.readFile("." + urlQuery.pathname, function (err, data) {
            if (err) {
                console.log(err.message);
                status = 404;
            }
            res.end(data);
        });
    }
});

server.listen(8080);