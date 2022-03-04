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
import com.example.onlineshopapp.db.viewmodel.UserEntityViewModel
import com.example.onlineshopapp.ui.component.TopAppView
import com.example.onlineshopapp.util.ThisApp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(mainActivity: MainActivity) {
    val navController = rememberNavController()
    var fullScreen by remember { mutableStateOf(false) }
    var showHomeIcon by remember { mutableStateOf(false) }
    val basketViewModel = ViewModelProvider(mainActivity).get(BasketEntityViewModel::class.java)
    val userEntityViewModel = ViewModelProvider(mainActivity).get(UserEntityViewModel::class.java)
    basketViewModel.getBasketListLive().observe(mainActivity) {
        basketViewModel.dataList.value = it
    }
    userEntityViewModel.getCurrentUser().observe(mainActivity) {
        userEntityViewModel.currentUserEntity.value = it
    }

    Scaffold(
        topBar = {
            if (!fullScreen)
                TopAppView(navController, basketViewModel, userEntityViewModel, showHomeIcon)
        }
    ) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                showHomeIcon = false
                fullScreen = false
                HomeScreen(navController)
            }
            composable("basket") {
                fullScreen = true
                BasketListScreen(navController, basketViewModel)
            }
            composable("proceedToPayment") {
                fullScreen = true
                UserPaymentScreen(navController, basketViewModel, userEntityViewModel, mainActivity)
            }
            composable("product/{categoryId}/{categoryTitle}",
                arguments = listOf(
                    navArgument("categoryId") { type = NavType.IntType },
                    navArgument("categoryTitle") { type = NavType.StringType }
                )
            ) { backStack ->
                showHomeIcon = false
                fullScreen = false
                val categoryId = backStack.arguments?.getInt("categoryId")
                val categoryTitle = backStack.arguments?.getString("categoryTitle")
                ThisApp.categoryId = categoryId!!
                ProductScreen(categoryId = categoryId,
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
                deepLinks = listOf(navDeepLink { uriPattern = "app://onlineshop.ir/{id}" }),
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                showHomeIcon = true
                fullScreen = false
                if (userEntityViewModel.currentUserEntity.value != null) {
                    ThisApp.token = userEntityViewModel.currentUserEntity.value!!.token!!
                    ThisApp.invoiceId = backStackEntry.arguments?.getInt("id")!!
                }
                InvoiceScreen(navController, backStackEntry.arguments?.getInt("id"))
            }
            composable("loginScreen") {
                fullScreen = true
                LoginScreen(navController, userEntityViewModel)
            }
            composable("dashboard") {
                fullScreen = true
                DashboardScreen(navController, userEntityViewModel)
            }
            composable("invoices") {
                fullScreen = true
                InvoiceListScreen(navController)
            }
            composable("editProfile") {
                fullScreen = true
                EditProfileScreen(navController, userEntityViewModel)
            }
        }
    }
}
