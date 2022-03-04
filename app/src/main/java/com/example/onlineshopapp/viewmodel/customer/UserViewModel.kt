package com.example.onlineshopapp.viewmodel.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.customer.User
import com.example.onlineshopapp.model.customer.UserVM
import com.example.onlineshopapp.repository.customer.UserRepository
import com.example.onlineshopapp.util.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private var repository: UserRepository) :
    ViewModel() {

    var token: String = ThisApp.token

    fun getUser(onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getUser(token)
            onResponse(response)
        }
    }

    fun changePass(data: UserVM, onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            val response = repository.changePass(data, token)
            onResponse(response)
        }
    }

    fun login(data: UserVM, onResponse: (response: ServiceResponse<UserVM>) -> Unit) {
        viewModelScope.launch {
            val response = repository.login(data)
            onResponse(response)
        }
    }

    fun register(data: UserVM, onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            val response = repository.register(data)
            onResponse(response)
        }
    }

    fun update(data: UserVM, onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            val response = repository.update(data, token)
            onResponse(response)
        }
    }

}