package com.example.comviet.Respository

import com.example.comviet.Network.AuthResponse
import com.example.comviet.Network.LoginRequest
import com.example.comviet.Network.RegisterRequest
import com.example.comviet.Network.RetrofitInstance
import retrofit2.Response

class AuthRespository {
    private val api = RetrofitInstance.api

    //Đăng ký người dùng
    suspend fun registerUser(name:String, username:String, email: String, password:String) : Response<AuthResponse>{
        val request = RegisterRequest(name, username, email,password)
        return api.registerUser(request)
    }
    //Đăng nhập người dùng
    suspend fun loginUser(email: String,password: String):Response<AuthResponse>{
        val request = LoginRequest(email,password)
        return api.loginUser(request)
    }
}

