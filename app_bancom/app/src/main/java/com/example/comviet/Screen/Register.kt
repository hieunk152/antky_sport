package com.example.comviet.Screen

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.comviet.Screen.ui.theme.ComvietTheme
import com.example.comviet.ViewModel.AuthViewModel

class Register : ComponentActivity() {
    private val authViewModel = AuthViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComvietTheme {
                RegisterScreen(onBackToLogin = {navigateUpToLogin()},authViewModel)
            }
        }
    }

    private fun navigateUpToLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onBackToLogin:()->Unit,authViewModel: AuthViewModel){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()){
        // Lớp phủ tối để nhìn thấy nội dung
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )

        //Thẻ đăng ký với hiệu ứng đổ bóng
        Card(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.Center)
                .zIndex(1f),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.5f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ){
            Column(modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Text(
                    text = "Đăng ký",
                    fontSize = 30.sp,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Tài khoản",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    color = Color.Black
                )
                OutlinedTextField(
                    value = username,
                    onValueChange = {username = it},
                    placeholder = { Text("Tên tài khoản") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black // Màu viền khi được chọn
                        , unfocusedBorderColor = Color.Gray, // Màu viền khi không được chọn
                        cursorColor = Color.Black //Màu của con trỏ
                    )

                )
                Text(
                    text = "Mật khẩu",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    color = Color.Black
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    placeholder = { Text("Mật khẩu") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black // Màu viền khi được chọn
                        , unfocusedBorderColor = Color.Gray, // Màu viền khi không được chọn
                        cursorColor = Color.Black //Màu của con trỏ
                    )
                )
                Text(
                    text = "Nhập lại mật khẩu",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    color = Color.Black
                )
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {confirmPassword = it},
                    placeholder = { Text("Nhập lại mật khẩu") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black // Màu viền khi được chọn
                        , unfocusedBorderColor = Color.Gray, // Màu viền khi không được chọn
                        cursorColor = Color.Black //Màu của con trỏ
                    )
                )
                Text(
                    text = "Email",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    color = Color.Black
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = {email = it},
                    placeholder = { Text("Nhập email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black // Màu viền khi được chọn
                        , unfocusedBorderColor = Color.Gray, // Màu viền khi không được chọn
                        cursorColor = Color.Black //Màu của con trỏ
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                //check lỗi
                if(errorMessage != null){
                    Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error)
                }

                //Nút đăng ký và nút trở lại
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(onClick = {
                        if(password == confirmPassword){
                            authViewModel.registerUser(username,username,email,password,
                                onSuccess = {
                                    Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                                    onBackToLogin() // Chuyển sang màn hình đăng nhập sau khi đăng ký thành công
                                },
                                onError = { error ->
                                    Toast.makeText(context, "Lỗi: $error", Toast.LENGTH_SHORT).show()
                                },context
                            )
                        }else{
                            Toast.makeText(context, "Kiểm tra lại mật khẩu", Toast.LENGTH_SHORT).show()
                        }
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black, // Màu nền của button
                        contentColor = Color.White // Màu của chu va icon ben trong
                    ),
                        modifier = Modifier.weight(1f).height(50.dp).padding(end = 8.dp)
                        )
                        {
                            Text("Đăng ký", fontSize = 18.sp)
                        }
                    Button(
                        onClick = { onBackToLogin() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black, // Màu nền của button
                            contentColor = Color.White // Màu của chữ và icon bên trong button
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .padding(start = 8.dp)

                    ) {
                        Text(text = "Trở lại", fontSize = 18.sp)
                    }
                }
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    // Tạo một mock AuthViewModel không có chức năng thực
    val mockAuthViewModel = AuthViewModel()

    // Gọi màn hình RegisterScreen với các giá trị giả
    RegisterScreen(
        onBackToLogin = {}, // Hành động giả khi nhấn "Trở lại"
        authViewModel = mockAuthViewModel
    )
}