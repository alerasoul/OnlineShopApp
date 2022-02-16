package com.example.onlineshopapp.ui.screen

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.onlineshopapp.MainActivity
import com.example.onlineshopapp.db.viewmodel.BasketEntityViewModel
import com.example.onlineshopapp.ui.component.TopAppView
import com.example.onlineshopapp.util.ThisApp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(mainActivity: MainActivity) {
    val navController = rememberNavController()
    var fullScreen by remember { mutableStateOf(false) }
    val basketViewModel = ViewModelProvider(mainActivity).get(BasketEntityViewModel::class.java)
    basketViewModel.getBasketListLive().observe(mainActivity) {
        basketViewModel.dataList.value = it
    }

    Scaffold(
        topBar = {
            if (!fullScreen)
                TopAppView(navController, basketViewModel)
        }
    ) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                fullScreen = false
                HomeScreen(navController)
            }
            composable("basket") {
                fullScreen = true
                BasketListScreen(navController, basketViewModel)
            }
            composable("proceedToPayment") {
                fullScreen = true
                UserPaymentScreen(navController,basketViewModel,mainActivity)
            }
            composable("product/{categoryId}/{categoryTitle}",
                arguments = listOf(
                    navArgument("categoryId") { type = NavType.IntType },
                    navArgument("categoryTitle") { type = NavType.StringType }
                )
            ) { backStack ->
                fullScreen = false
                val categoryId = backStack.arguments?.getInt("categoryId")
                val categoryTitle = backStack.arguments?.getString("categoryTitle")
                ThisApp.categoryId = categoryId!!
                ProductScreen(categoryId = categoryId!!,
                    categoryTitle = categoryTitle!!,
                    navController = navController)

            }
            composable("showProduct/{productId}",
                arguments = listOf(
                    navArgument("productId") { type = NavType.IntType }
                )
            ) { backStack ->
                fullScreen = true
                backStack.arguments?.getInt("productId").let {
                    ShowProductScreen(it!!, navController, basketViewModel = basketViewModel)
                }
            }
            composable(
                "invoice/{id}",
                deepLinks = listOf(navDeepLink { uriPattern="app://onlineshop.ir/{id}" })
            ){  backStackEntry ->
                InvoiceScreen(navController, backStackEntry.arguments?.getInt("id"))
            }
        }
    }
}
