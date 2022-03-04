package com.example.onlineshopapp.ui.screen

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp.db.viewmodel.UserEntityViewModel
import com.example.onlineshopapp.model.customer.UserVM
import com.example.onlineshopapp.viewmodel.customer.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(
    navController: NavHostController,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel(),
) {
    var isLoading by remember { mutableStateOf(false) }
    val currentUser by remember { mutableStateOf(userEntityViewModel.currentUserEntity) }
    val isLoggedIn by remember { mutableStateOf(userEntityViewModel.currentUserEntity.value != null) }
    var firstName by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.firstName!! else "")) }
    var firstNameError by remember { mutableStateOf(false) }
    var lastName by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.lastName!! else "")) }
    var lastNameError by remember { mutableStateOf(false) }
    var phone by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.phone!! else "")) }
    var phoneError by remember { mutableStateOf(false) }
    var address by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.address!! else "")) }
    var addressError by remember { mutableStateOf(false) }
    var postalCode by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.postalCode!! else "")) }
    var postalCodeError by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.username!! else "")) }
    var usernameError by remember { mutableStateOf(false) }

    Column {
        Row {
            IconButton(onClick = { navController.popBackStack() }, Modifier.width(60.dp)) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(0.dp, 0.dp, 60.dp, 0.dp)
                    .wrapContentHeight(),
                text = "Shopping Card",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
            )
        }
        LazyColumn(Modifier.padding(20.dp, 0.dp)) {
            item {
                OutlinedTextField(
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    value = firstName,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next),
                    onValueChange = {
                        firstName = it
                        firstNameError = false
                    },
                    label = { Text(text = "First Name") },
                    trailingIcon = {
                        if (firstNameError)
                            Icon(imageVector = Icons.Filled.Warning,
                                contentDescription = "error",
                                tint = Color.Red)
                    }
                )
                if (firstNameError) {
                    Text(text = "Please enter your first name", color = Color.Red, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    value = lastName,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next),
                    onValueChange = {
                        lastName = it
                        lastNameError = false
                    },
                    label = { Text(text = "Last Name") },
                    trailingIcon = {
                        if (lastNameError)
                            Icon(imageVector = Icons.Filled.Warning,
                                contentDescription = "error",
                                tint = Color.Red)
                    }
                )
                if (lastNameError) {
                    Text(text = "Please enter your last name", color = Color.Red, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    value = username,
                    enabled = currentUser.value == null,
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
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    value = phone,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next),
                    onValueChange = {
                        phone = it
                        phoneError = false
                    },
                    label = { Text(text = "Phone") },
                    trailingIcon = {
                        if (phoneError) {
                            Icon(imageVector = Icons.Filled.Warning,
                                contentDescription = "error",
                                tint = Color.Red)
                        }
                    }
                )
                if (phoneError) {
                    Text(text = "Please enter your phone", color = Color.Red, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    singleLine = false,
                    modifier = Modifier.fillMaxWidth(),
                    value = postalCode,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next),
                    onValueChange = {
                        postalCode = it
                        postalCodeError = false
                    },
                    label = { Text(text = "Postal Code") },
                    trailingIcon = {
                        if (postalCodeError) {
                            Icon(imageVector = Icons.Filled.Warning,
                                contentDescription = "error",
                                tint = Color.Red)
                        }
                    }
                )
                if (postalCodeError) {
                    Text(text = "Please enter your postal code",
                        color = Color.Red,
                        fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    singleLine = false,
                    modifier = Modifier.fillMaxWidth(),
                    value = address,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = {
                        address = it
                        addressError = false
                    },
                    label = { Text(text = "Address") },
                    trailingIcon = {
                        if (addressError) {
                            Icon(imageVector = Icons.Filled.Warning,
                                contentDescription = "error",
                                tint = Color.Red)
                        }
                    }
                )
                if (addressError) {
                    Text(text = "Please enter your address", color = Color.Red, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(40.dp))

                if (!isLoading) {
                    Button(
                        onClick = {
                            if (firstName.text.isEmpty())
                                firstNameError = true
                            if (lastName.text.isEmpty())
                                lastNameError = true
                            if (username.text.isEmpty())
                                usernameError = true
                            if (phone.text.isEmpty())
                                phoneError = true
                            if (postalCode.text.isEmpty())
                                postalCodeError = true
                            if (address.text.isEmpty())
                                addressError = true
                            if (firstNameError || lastNameError || usernameError || phoneError || postalCodeError || addressError)
                                return@Button
                            val userInfo = UserVM(
                                id = currentUser.value!!.id,
                                customerId = currentUser.value!!.customerId,
                                username = username.text,
                                firstName = firstName.text,
                                lastName = lastName.text,
                                phone = phone.text,
                                address = address.text,
                                postalCode = postalCode.text,
                            )
                            userInfo.token = currentUser.value!!.token!!
                            isLoading = true
                            userViewModel.update(userInfo) { response ->
                                if (response.status == "OK") {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        val userEntity = userInfo.convertToUserEntity()
                                        userEntity.id = currentUser.value!!.id
                                        userEntity.token = currentUser.value!!.token!!
                                        userEntityViewModel.update(userEntity)
                                    }
                                    isLoading = false
                                    navController.popBackStack()
                                }
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
                            text = "Update Profile",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.5.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                if (isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

            }
        }
    }
}