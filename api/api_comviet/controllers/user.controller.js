const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const User = require('../model/user');
const net = require("node:net");


// Đăng ký mã hóa mk
exports.reg =  async (req, res) => {
    const { name, username, email, password } = req.body;
    try {
        // Kiểm tra xem email hoặc username đã tồn tại chưa
        let user = await User.findOne({ $or: [{ email }, { username }] });
        console.log(user);
        if (user) {
            return res.status(400).json({ msg: 'Email hoặc username đã tồn tại' });
        }

        // Mã hóa mật khẩu
        const salt = await bcrypt.genSalt(10);
        const hashedPassword = await bcrypt.hash(password, salt);

        // Tạo người dùng mới
        user = new User({
            name,
            username,
            email,
            password: hashedPassword,
        });

        await user.save();
        res.status(201).json({ msg: 'Đăng ký thành công' });

    } catch (error) {
        res.status(500).json({ msg: 'Lỗi server' });
    }
}
// Đăng nhập mã hóa mk
exports.login =  async (req, res) => {
    const { email, password } = req.body;
    try {
        // Tìm người dùng theo email
        const user = await User.findOne({ email });
        if (!user) {
            return res.status(400).json({ msg: 'Email không tồn tại' });
        }
        // Kiểm tra mật khẩu
        const isMatch = await bcrypt.compareSync(password, user.password);
        if (!isMatch) {
            return res.status(400).json({ msg: 'Mật khẩu không đúng' });
        }
        // Tạo token
        const token = jwt.sign(
            { userId: user._id, username: user.username },
            process.env.JWT_SECRET,
            { expiresIn: '1h' }
        );
        console.log("token "+token);


        const decoded = jwt.verify(token, process.env.JWT_SECRET);

        console.log("decoded"+decoded);

        res.json({ token });
    } catch (error) {
        console.error(error)
        res.status(500).json({ msg: 'Lỗi server' });
    }

};