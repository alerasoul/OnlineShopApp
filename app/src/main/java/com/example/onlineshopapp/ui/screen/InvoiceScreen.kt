package com.example.onlineshopapp.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun InvoiceScreen(navController: NavController, id: Int?) {
    Text(text = id.toString())
}