var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/helloworld', function(req, res, next) {
  res.render('helloworld', { title: 'Greetings, Universe!' });
});

router.get("/torresdate", function(req, res, next) {
  res.render('torresdate', { title: 'Torres Date' });
});

module.exports = router;