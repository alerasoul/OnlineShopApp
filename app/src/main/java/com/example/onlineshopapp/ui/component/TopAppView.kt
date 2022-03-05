package com.example.onlineshopapp.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlineshopapp.db.viewmodel.BasketEntityViewModel
import com.example.onlineshopapp.db.viewmodel.UserEntityViewModel

@Composable
fun TopAppView(
    navController: NavHostController,
    basketViewModel: BasketEntityViewModel,
    userEntityViewModel: UserEntityViewModel,
    showHomeIcon: Boolean,
) {
    val animatedVisibleState = remember { MutableTransitionState(false) }
        .apply { targetState = true }

    TopAppBar(
        title = {
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
                Text(text = "Online Shop", fontSize = 25.sp)
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            if (showHomeIcon) {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = "")
                }
            }
            AnimatedVisibility(
                visibleState = animatedVisibleState,
                enter = slideInVertically(
                    animationSpec = tween(1000),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(1000)
                ),
                exit = fadeOut()
            )
            {
                IconButton(onClick = { navController.navigate("basket") }) {
                    Box(contentAlignment = Alignment.BottomEnd) {
                        Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "")
                        if (basketViewModel.dataList.value.isNotEmpty()) {
                            Card(
                                shape = RoundedCornerShape(50.dp),
                                backgroundColor = Color.Red,
                                modifier = Modifier.size(14.dp)
                            ) {
                                Text(
                                    text = "${basketViewModel.dataList.value.size}",
                                    color = Color.White,
                                    fontSize = 8.sp,
                                    modifier = Modifier.padding(1.dp),
                                    textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }
            AnimatedVisibility(
                visibleState = animatedVisibleState,
                enter = slideInVertically(
                    animationSpec = tween(1500),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(1500)
                ),
                exit = fadeOut()
            ) {
                IconButton(onClick = {
                    if (!userEntityViewModel.isLoggedIn())
                        navController.navigate("loginScreen")
                    else
                        navController.navigate("dashboard")
                }) {
                    Icon(imageVector = Icons.Outlined.Person, contentDescription = "")
                }
            }
        }
    )
}