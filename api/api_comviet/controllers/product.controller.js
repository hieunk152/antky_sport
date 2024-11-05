const Product = require('../model/product');

exports.getListProduct = async (req, res) => {
    try{
        const products = await Product.find();// lấy tất cả các sp từ database
        res.json(products);
    }catch (error) {
        res.status(500).send({msg: "Error getting product"});
    }
};
exports.getProductByName = async (req, res) => {
    try {
        const product = await Product.findOne({ name: req.body.name });
        res.json(product);
    }catch (error) {
        res.status(500).send({msg: "Error getting product"});
    }
};
exports.addProduct = async (req, res) => {
    const {name, image_url, price,description} = req.body;
    try{
        const product = new Product({
            name,image_url,price,description
        });
        await product.save();
        res.status(201).json({msg: "Product added successfully"});
    }catch (error) {
        res.status(500).send({msg: "Error adding product"});
    }
}