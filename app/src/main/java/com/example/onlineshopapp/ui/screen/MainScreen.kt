package com.example.onlineshopapp.ui.screen

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.onlineshopapp.ui.component.TopAppView

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var fullScreen by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            if (!fullScreen)
                TopAppView()
        }
    ) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                fullScreen = false
                HomeScreen(navController)
            }
            composable("showProduct/{productId}",
                arguments = listOf(
                    navArgument("productId") { type = NavType.IntType }
                )
            ) { backStack ->
                fullScreen = true
                backStack.arguments?.getInt("productId").let {
                    ShowProductScreen(it!!, navController)
                }
            }
        }
    }
}
