package com.example.onlineshopapp.ui.screen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
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
import com.example.onlineshopapp.MainActivity
import com.example.onlineshopapp.db.viewmodel.BasketEntityViewModel
import com.example.onlineshopapp.db.viewmodel.UserEntityViewModel
import com.example.onlineshopapp.model.customer.UserVM
import com.example.onlineshopapp.model.invoice.InvoiceItem
import com.example.onlineshopapp.model.invoice.PaymentTransaction
import com.example.onlineshopapp.viewmodel.customer.UserViewModel
import com.example.onlineshopapp.viewmodel.invoice.TransactionViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun UserPaymentScreen(
    navController: NavController,
    basketEntityViewModel: BasketEntityViewModel,
    userEntityViewModel: UserEntityViewModel,
    mainActivity: MainActivity,
    transactionViewModel: TransactionViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
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
    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordError by remember { mutableStateOf(false) }

    val animatedVisibleState = remember { MutableTransitionState(false) }
        .apply { targetState = true }

    Column {
        Row {
            AnimatedVisibility(
                visibleState = animatedVisibleState,
                enter = slideInVertically(
                    animationSpec = tween(500),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(500)
                ),
                exit = fadeOut()
            )
            {
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
        }
        LazyColumn(Modifier.padding(20.dp, 0.dp)) {
            item {
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500, 500),
                        initialOffsetY = { -40 }
                    ) + fadeIn(
                        animationSpec = tween(500, 500)
                    ),
                    exit = fadeOut()
                )
                {
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
                }
                if (firstNameError) {
                    Text(text = "Please enter your first name", color = Color.Red, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))

                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500, 700),
                        initialOffsetY = { -40 }
                    ) + fadeIn(
                        animationSpec = tween(500, 700)
                    ),
                    exit = fadeOut()
                )
                {
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
                }
                if (lastNameError) {
                    Text(text = "Please enter your last name", color = Color.Red, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))

                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500, 900),
                        initialOffsetY = { -40 }
                    ) + fadeIn(
                        animationSpec = tween(500, 900)
                    ),
                    exit = fadeOut()
                )
                {
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
                }
                if (usernameError) {
                    Text(text = "Please enter your username", color = Color.Red, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))

                if (currentUser.value == null) {
                    AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500, 1100),
                            initialOffsetY = { -40 }
                        ) + fadeIn(
                            animationSpec = tween(500, 1100)
                        ),
                        exit = fadeOut()
                    )
                    {
                        OutlinedTextField(
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            value = password,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next),
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
                    }
                    if (passwordError) {
                        Text(text = "Please enter your password",
                            color = Color.Red,
                            fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500, 1300),
                        initialOffsetY = { -40 }
                    ) + fadeIn(
                        animationSpec = tween(500, 1300)
                    ),
                    exit = fadeOut()
                )
                {
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
                }
                if (phoneError) {
                    Text(text = "Please enter your phone", color = Color.Red, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))

                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500, 1500),
                        initialOffsetY = { -40 }
                    ) + fadeIn(
                        animationSpec = tween(500, 1500)
                    ),
                    exit = fadeOut()
                )
                {
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
                }
                if (postalCodeError) {
                    Text(text = "Please enter your postal code",
                        color = Color.Red,
                        fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))

                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500, 1700),
                        initialOffsetY = { -40 }
                    ) + fadeIn(
                        animationSpec = tween(500, 1700)
                    ),
                    exit = fadeOut()
                )
                {
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
                }
                if (addressError) {
                    Text(text = "Please enter your address", color = Color.Red, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(40.dp))

                if (!isLoading) {
                    AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500, 1900),
                            initialOffsetY = { -40 }
                        ) + fadeIn(
                            animationSpec = tween(500, 1900)
                        ),
                        exit = fadeOut()
                    )
                    {
                        Button(
                            onClick = {
                                if (firstName.text.isEmpty())
                                    firstNameError = true
                                if (lastName.text.isEmpty())
                                    lastNameError = true
                                if (username.text.isEmpty())
                                    usernameError = true
                                if (currentUser.value == null && password.text.isEmpty())
                                    passwordError = true
                                if (phone.text.isEmpty())
                                    phoneError = true
                                if (postalCode.text.isEmpty())
                                    postalCodeError = true
                                if (address.text.isEmpty())
                                    addressError = true
                                if (firstNameError || lastNameError || usernameError || passwordError || phoneError || postalCodeError || addressError)
                                    return@Button
                                val userInfo = UserVM(
                                    id = if (currentUser.value == null) null else currentUser.value!!.id,
                                    customerId = if (currentUser.value == null) null else currentUser.value!!.customerId,
                                    username = username.text,
                                    password = password.text,
                                    firstName = firstName.text,
                                    lastName = lastName.text,
                                    phone = phone.text,
                                    address = address.text,
                                    postalCode = postalCode.text,
                                )
                                val items = ArrayList<InvoiceItem>()
                                basketEntityViewModel.dataList.value.forEach {
                                    items.add(InvoiceItem.convertFromBasket(it))
                                }
                                isLoading = true
                                val request = PaymentTransaction(items = items, user = userInfo)
                                transactionViewModel.goToPayment(request) { response ->
                                    if (response.status == "OK" && response.data!!.isNotEmpty()) {
                                        if (currentUser.value == null) {
                                            userViewModel.login(UserVM(username = username.text,
                                                password = password.text)) { userResponse ->
                                                if (userResponse.status == "OK") {
                                                    val user = userResponse.data!![0]
                                                    CoroutineScope(Dispatchers.IO).launch {
                                                        userEntityViewModel.insert(user.convertToUserEntity())
                                                    }
                                                }
                                            }
                                        }
                                        CoroutineScope(Dispatchers.IO).launch {
                                            basketEntityViewModel.deleteAll()
                                            mainActivity.finish()
                                        }
                                        val intent =
                                            Intent(Intent.ACTION_VIEW, Uri.parse(response.data[0]))
                                        context.startActivity(intent)
                                    } else if (response.message!!.isNotEmpty())
                                        Toast.makeText(context,
                                            response.message,
                                            Toast.LENGTH_SHORT)
                                            .show()
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
                                text = "\$Pay",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.5.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                        }
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

