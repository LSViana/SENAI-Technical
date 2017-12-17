var mysql = require("mysql");

var con = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "worldskills",
    database: "node-intro"
});

var getCompanies = "SELECT * FROM `company`";

con.connect(function(err) {
    if(err) throw err;
    console.log("Successfully Connected.");
    con.query(getCompanies, function(err, results, fields) {
        if(err) throw err;
        for (var i = 0; i < results.length; i++) {
            var result = results[i];
            for(var field in result) {
                console.log(result[field] + "\t");
            }
        }
    })
});

setInterval(function() {
    con.end();
    process.exit(0);
}, 2500);