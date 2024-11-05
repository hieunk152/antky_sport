const mongoose = require('mongoose');
require('dotenv').config(); // su dung thu vien doc file env:   npm install dotenv --save
const DB_NAME = process.env.DB_NAME;

mongoose.connect( 'mongodb+srv://admin:c43nLpjuQXu6w7M5@cluster0.mbz9o.mongodb.net/'+ DB_NAME).then(()=>{
    console.log("Connected to the DB successfully!");
})
    .catch( (err) =>{
        console.log("Loi ket noi CSDL");
        console.log(err);
    });

module.exports = {mongoose}
