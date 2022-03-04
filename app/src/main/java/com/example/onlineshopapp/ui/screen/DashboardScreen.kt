package com.example.onlineshopapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlineshopapp.db.viewmodel.UserEntityViewModel
import com.example.onlineshopapp.ui.component.AdvancedButton
import com.example.onlineshopapp.ui.theme.Light1
import com.example.onlineshopapp.ui.theme.Light2
import com.example.onlineshopapp.ui.theme.Light3
import com.example.onlineshopapp.ui.theme.Light4
import com.example.onlineshopapp.util.ThisApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun DashboardScreen(
    navController: NavController,
    userEntityViewModel: UserEntityViewModel,
) {
    val currentUser = userEntityViewModel.currentUserEntity.value
    Column() {
        Row(Modifier.padding(20.dp)) {
            IconButton(onClick = { navController.popBackStack() }, Modifier.width(60.dp)) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 6.dp, 60.dp, 0.dp),
                text = "User Profile",
                fontWeight = FontWeight.Bold,
                fontSize = 23.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row(Modifier.padding(20.dp).fillMaxWidth()) {
            Card(modifier = Modifier.size(70.dp),
                backgroundColor = Color.LightGray,
                shape = RoundedCornerShape(26.dp)) {
                Icon(modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Filled.Person, tint = Color.White,
                    contentDescription = "")
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)) {
                Text(
                    text = "${currentUser!!.firstName} ${currentUser.lastName}",
                    fontSize = 26.sp,
                    color = Color.Black,
                )
                Text(
                    text = "${currentUser.username}",
                    fontSize = 21.sp,
                    color = Color.Gray,
                )
            }
            IconButton(
                onClick = {
                    ThisApp.token = currentUser!!.token!!
                    navController.navigate("editProfile")
                }
            ) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "")
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 10.dp),
            text = "Account",
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp,
            color = Color.Black,
        )
        LazyColumn {
            item {
                AdvancedButton("Invoices", "Show your invoices", Icons.Filled.Star, Light1) {
                    ThisApp.userId = currentUser!!.userId!!
                    ThisApp.token = currentUser.token!!
                    navController.navigate("invoices")
                }
            }
            item {
                AdvancedButton("Change Password",
                    "Change your password",
                    Icons.Filled.Lock,
                    Light3) {}
            }
            item {
                AdvancedButton("About", "About the application", Icons.Outlined.Info, Light2) {}
            }
            item {
                AdvancedButton("Logout", "Logout", Icons.Outlined.ExitToApp, Light4) {
                    CoroutineScope(Dispatchers.IO).launch {
                        userEntityViewModel.deleteAll()
                    }
                    navController.navigate("home")
                }
            }
        }
    }
}