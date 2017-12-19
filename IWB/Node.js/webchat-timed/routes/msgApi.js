const express = require("express");
const url = require("url");
const connectionProvider = require("../db/connectionProvider");
const router = express.Router();
const con = connectionProvider.getConnection("localhost", "root", "root132");

con.connect(function (err) {
    if (err) {
        console.log("Couldn't connect to database.");
        throw err;
    }
    console.log("Database successfully connected.");
    con.query("USE `webchat-timed`");
});

// Queries
const GETALL = "SELECT * FROM `message`";
const GETLASTTIME = "SELECT * FROM `last-message`";
const GETNOTRECEIVED = "SELECT * FROM `message` WHERE datetime >= ?";
const INSERTMESSAGE = "INSERT INTO `message`(content, author, datetime) VALUES(?, 'Standard', NOW())";

/**
 * Method to supply all messages through a JSON notation
 */
router.get("/messages", function (req, res) {
    res.statusCode = 200;
    res.setHeader("Content-Type", "application/json");
    con.query(GETALL, function (err, results, fields) {
        if (err) res.statusCode = 500;
        else {
            res.write(JSON.stringify(results));
        }
        res.end();
    });
});

/**
 * Method to return the time of the last message inserted
 */
router.get("/last-message", function (req, res) {
    res.statusCode = 200;
    res.setHeader("Content-Type", "text/plain");
    con.query(GETLASTTIME, function (err, results, fields) {
        if (err) res.statusCode = 500;
        else {
            res.write(results[0].UPDATE_TIME.toISOString());
        }
        res.end();
    })
});

/**
 * Get all messages that have time higher or equal than the supplied time at 'date' parameter
 */
router.get("/unreceived", function (req, res) {
    // Getting data from URL
    var query = url.parse(req.url, true);
    var queryData = query.query;
    // Responding
    res.statusCode = 200;
    res.setHeader("Content-Type", "application/json");
    if (queryData.date) {
        var dateToQuery = new Date(queryData.date);
        con.query(GETNOTRECEIVED, dateToQuery, function (err, results, fields) {
            if (err) res.statusCode = 500;
            else {
                res.write(JSON.stringify(results));
            }
            res.end();
        });
    } else {
        res.statusCode = 500;
        res.end();
    }
});

router.post("/messages", function (req, res) {
    res.writeHead(200, {
        "Access-Control-Allow-Methods": "GET, PUT, POST, DELETE, OPTIONS",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Max-Age": 86400,
        'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
        "Content-Type": "application/json"
    });
    res.write(JSON.stringify({
        msg: "Viana top ♥"
    }), function() {
        var msgText = req.body.messageContent;
        con.query(INSERTMESSAGE, msgText, function(err, results, fields) { 
            if(err) {
                console.log("Error: " + err.message);
                res.statusCode = 500;
            }
            else {
                // Successfully added!
            }
            res.end();
        });
    });
});

module.exports = router;