var express = require('express');
var router = express.Router();
const p = require('../controllers/product.controller');
module.exports = router;

router.get("/",p.getListProduct);
router.get("/getProductByName/:name",p.getProductByName)
//
router.post('/add',p.addProduct);
// router.post('/update',p.updateProduct)
//
// router.delete('/delete/:id',p.delProduct);
