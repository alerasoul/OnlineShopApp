package com.example.onlineshopapp.viewmodel.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.customer.User
import com.example.onlineshopapp.model.customer.UserVM
import com.example.onlineshopapp.repository.customer.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private var repository: UserRepository) :
    ViewModel() {

    fun getUser(onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            //ToDo:FixMe:TOKEN
            var response = repository.getUser("")
            onResponse(response)
        }
    }

    fun changePass(data: UserVM, onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            //ToDo:FixMe:TOKEN
            var response = repository.changePass(data, "")
            onResponse(response)
        }
    }

    fun login(data: UserVM, onResponse: (response: ServiceResponse<UserVM>) -> Unit) {
        viewModelScope.launch {
            var response = repository.login(data)
            onResponse(response)
        }
    }

    fun register(data: UserVM, onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            var response = repository.register(data)
            onResponse(response)
        }
    }

    fun update(data: UserVM, onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            //ToDo:FixMe:TOKEN
            var response = repository.update(data, "")
            onResponse(response)
        }
    }

}