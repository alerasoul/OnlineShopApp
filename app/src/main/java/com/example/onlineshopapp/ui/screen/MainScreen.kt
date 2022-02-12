package com.example.onlineshopapp.ui.screen

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onlineshopapp.ui.component.TopAppView

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var fullScreen by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppView()
        }
    ) {
        NavHost(navController = navController, startDestination = "Home") {
            composable("Home") {
                fullScreen = false
                HomeScreen()
            }
        }
    }
}
