package com.example.onlineshopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshopapp.db.viewmodel.UserEntityViewModel
import com.example.onlineshopapp.ui.screen.SplashScreen
import com.example.onlineshopapp.ui.theme.OnlineShopAppTheme
import com.example.onlineshopapp.util.ThisApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isLoad by remember { mutableStateOf(false) }
            val userEntityViewModel = ViewModelProvider(this).get(UserEntityViewModel::class.java)
            userEntityViewModel.getCurrentUser().observe(this) {
                isLoad = true
                userEntityViewModel.currentUserEntity.value = it
            }
            OnlineShopAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    if (userEntityViewModel.currentUserEntity.value != null)
                        ThisApp.token = userEntityViewModel.currentUserEntity.value!!.token!!
                    if (isLoad)
                        SplashScreen(this, userEntityViewModel)
                }
            }
        }
    }
}