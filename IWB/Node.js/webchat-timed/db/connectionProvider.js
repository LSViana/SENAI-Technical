const mysql = require("mysql");

/**
 * Return a connection object, not yet connected, using the supplied data
 * @param {String} hostname Hostname of the machine where the server is located at
 * @param {String} user User to authenticate at database
 * @param {String} password Password to authenticate at database
 */
exports.getConnection = function getConnection(hostname, user, password) {
    return mysql.createConnection({
        "host": hostname,
        "user": user,
        "password": password
    });
}