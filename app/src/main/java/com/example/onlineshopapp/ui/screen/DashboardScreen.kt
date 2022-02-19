package com.example.onlineshopapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.navigation.NavHostController
import com.example.onlineshopapp.db.viewmodel.UserEntityViewModel
import com.example.onlineshopapp.ui.component.AdvancedButton
import com.example.onlineshopapp.ui.theme.Light1
import com.example.onlineshopapp.ui.theme.Light2
import com.example.onlineshopapp.ui.theme.Light3
import com.example.onlineshopapp.ui.theme.Light4


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardScreen(
    navController: NavHostController,
    userEntityViewModel: UserEntityViewModel,
) {
    var currentUser = userEntityViewModel.currentUserEntity.value
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
        Row(Modifier.padding(20.dp)) {
            Card(modifier = Modifier.size(70.dp),
                backgroundColor = Color.LightGray,
                shape = RoundedCornerShape(26.dp)) {
                Icon(modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Filled.Person, tint = Color.White,
                    contentDescription = "")
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = "${currentUser!!.firstName} ${currentUser.lastName}",
                    fontSize = 26.sp,
                    color = Color.Black,
                )
                Text(
                    text = "${currentUser!!.username}",
                    fontSize = 21.sp,
                    color = Color.Gray,
                )
            }
            Spacer(modifier = Modifier.height(100.dp))
            IconButton(onClick = { navController.popBackStack() },
                Modifier
                    .fillMaxWidth()
                    .size(40.dp)
                    .weight(1f)
                    .padding(100.dp, 0.dp, 0.dp, 0.dp)) {
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
                AdvancedButton("Invoices", "Show your invoices", Icons.Filled.Star, Light1) {}
            }
            item {
                AdvancedButton("About", "About the application", Icons.Outlined.Info, Light2) {}
            }
            item {
                AdvancedButton("Help", "Help and feedback", Icons.Filled.Phone, Light3) {}
            }
            item {
                AdvancedButton("Logout", "Logout", Icons.Outlined.ExitToApp, Light4) {}
            }
        }
    }
}