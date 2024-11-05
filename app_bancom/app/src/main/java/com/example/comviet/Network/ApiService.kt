package com.example.comviet.Network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

data class RegisterRequest(
    val name: String,
    val username:String,
    val email: String,
    val password:String
)
data class LoginRequest(
    val email: String,
    val password: String
)
data class AuthResponse(
    val token:String
)
interface ApiService{
    //Api đăng ký
    @POST("/users/net")
    suspend fun registerUser(@Body request: RegisterRequest): Response<AuthResponse>
    //Api đăng nhập
    @POST("/users/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<AuthResponse>
}

//Đối tượng Retrofit để tạo các Api service
object RetrofitInstance{
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.24.29.43:3000/") //Địa chỉ IP your device
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}