var express = require('express');
var router = express.Router();
const uc = require("../controllers/user.controller")

router.post('/net',uc.reg)
router.post('/login',uc.login)
module.exports = router;
