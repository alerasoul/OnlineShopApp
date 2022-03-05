package com.example.onlineshopapp.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
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
import androidx.compose.runtime.remember
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

    val animatedVisibleState = remember { MutableTransitionState(false) }
        .apply { targetState = true }


    Column {
        Row(Modifier.padding(20.dp)) {
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
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row(Modifier
            .padding(20.dp)
            .fillMaxWidth()) {
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
                Card(modifier = Modifier.size(70.dp),
                    backgroundColor = Color.LightGray,
                    shape = RoundedCornerShape(26.dp)) {
                    Icon(modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Filled.Person, tint = Color.White,
                        contentDescription = "")
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)) {
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500, 600),
                        initialOffsetY = { -40 }
                    ) + fadeIn(
                        animationSpec = tween(500, 600)
                    ),
                    exit = fadeOut()
                )
                {
                    Text(
                        text = "${currentUser!!.firstName} ${currentUser.lastName}",
                        fontSize = 26.sp,
                        color = Color.Black,
                    )
                }
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
                    Text(
                        text = "${currentUser!!.username}",
                        fontSize = 21.sp,
                        color = Color.Gray,
                    )
                }
            }
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
                IconButton(
                    onClick = {
                        ThisApp.token = currentUser!!.token!!
                        navController.navigate("editProfile")
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Edit, contentDescription = "")
                }
            }
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
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp),
                text = "Account",
                fontWeight = FontWeight.Bold,
                fontSize = 23.sp,
                color = Color.Black,
            )
        }
        LazyColumn {
            item {
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500, 1600),
                        initialOffsetY = { -40 }
                    ) + fadeIn(
                        animationSpec = tween(500, 1600)
                    ),
                    exit = fadeOut()
                )
                {
                    AdvancedButton("Invoices", "Show your invoices", Icons.Filled.Star, Light1) {
                        ThisApp.userId = currentUser!!.userId!!
                        ThisApp.token = currentUser.token!!
                        navController.navigate("invoices")
                    }
                }
            }
            item {
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500, 1800),
                        initialOffsetY = { -40 }
                    ) + fadeIn(
                        animationSpec = tween(500, 1800)
                    ),
                    exit = fadeOut()
                )
                {
                    AdvancedButton("Change Password",
                        "Change your password",
                        Icons.Filled.Lock,
                        Light3) {}
                }
            }
            item {
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500, 2000),
                        initialOffsetY = { -40 }
                    ) + fadeIn(
                        animationSpec = tween(500, 2000)
                    ),
                    exit = fadeOut()
                )
                {
                    AdvancedButton("About", "About the application", Icons.Outlined.Info, Light2) {}
                }
            }
            item {
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500, 2200),
                        initialOffsetY = { -40 }
                    ) + fadeIn(
                        animationSpec = tween(500, 2200)
                    ),
                    exit = fadeOut()
                )
                {
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
}