package com.example.onlineshopapp.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopapp.db.viewmodel.UserEntityViewModel
import com.example.onlineshopapp.model.customer.UserVM
import com.example.onlineshopapp.viewmodel.customer.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }

    var username by remember { mutableStateOf(TextFieldValue()) }
    var usernameError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordError by remember { mutableStateOf(false) }

    LazyColumn(Modifier.padding(20.dp, 0.dp)) {
        item {
            Column() {
                IconButton(onClick = { navController.popBackStack() }, Modifier.width(60.dp)) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Sign in",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Welcome back!",
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(25.dp))
            }
        }

        item {
            OutlinedTextField(
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                value = username,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next),
                onValueChange = {
                    username = it
                    usernameError = false
                },
                label = { Text(text = "Username") },
                trailingIcon = {
                    if (usernameError) {
                        Icon(imageVector = Icons.Filled.Warning,
                            contentDescription = "error",
                            tint = Color.Red)
                    }
                }
            )
            if (usernameError) {
                Text(text = "Please enter your username", color = Color.Red, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                value = password,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = {
                    password = it
                    passwordError = false
                },
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                trailingIcon = {
                    if (passwordError) {
                        Icon(imageVector = Icons.Filled.Warning,
                            contentDescription = "error",
                            tint = Color.Red)
                    }
                }
            )
            if (passwordError) {
                Text(text = "Please enter your password", color = Color.Red, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(40.dp))
            if (!isLoading) {
                Button(
                    onClick = {
                        if (username.text.isEmpty())
                            usernameError = true
                        if (password.text.isEmpty())
                            passwordError = true
                        if (usernameError || passwordError)
                            return@Button
                        isLoading = true
                        userViewModel.login(UserVM(username = username.text,
                            password = password.text)) { response ->
                            if (response.status == "OK") {
                                val user = response.data!![0]
                                CoroutineScope(Dispatchers.IO).launch {
                                    userEntityViewModel.insert(user.convertToUserEntity())
                                }
                                Toast.makeText(context,
                                    "Welcome dear ${user.firstName}",
                                    Toast.LENGTH_SHORT).show()
                                navController.navigate("home")
                            }
                            isLoading = false
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 0.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray
                    )
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically),
                        text = "Login",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}