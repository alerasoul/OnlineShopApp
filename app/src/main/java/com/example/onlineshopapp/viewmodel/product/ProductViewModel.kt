package com.example.onlineshopapp.viewmodel.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.product.Product
import com.example.onlineshopapp.repository.product.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private var repository: ProductRepository) :
    ViewModel() {

    fun getProducts(onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getProducts()
            onResponse(response)
        }
    }

    fun getNewProducts(onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getNewProducts()
            onResponse(response)
        }
    }

    fun getPopularProducts(onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getPopularProducts()
            onResponse(response)
        }
    }

    fun getProductById(id: Int, onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getProductById(id)
            onResponse(response)
        }
    }
}