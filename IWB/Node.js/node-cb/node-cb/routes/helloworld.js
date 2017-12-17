var express = require("express");
var router = express.Router();

/* GET home page. */
router.get("/helloworld", function(req, res) {
  res.render("helloworld", { title: "Greetings, Universe!" });
});

module.exports = router;