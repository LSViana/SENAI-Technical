const express = require('express');
const router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  // res.writeHead(200, {
  //   "Access-Control-Allow-Methods": "GET, OPTIONS",
  //   "Access-Control-Allow-Origin": "*",
  //   "Access-Control-Max-Age": 86400,
  //   "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token"
  // });
  res.render('index', { title: 'Express' });
});

module.exports = router;