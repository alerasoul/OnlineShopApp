package com.example.onlineshopapp.viewmodel.product

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.product.Product
import com.example.onlineshopapp.model.product.ProductCategory
import com.example.onlineshopapp.model.site.Slider
import com.example.onlineshopapp.repository.product.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private var repository: ProductRepository) :
    ViewModel() {

    var dataList = mutableStateOf<List<Product>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getProducts(0, 6) { response ->
            isLoading.value = false
            if (response.status == "OK")
                dataList.value = response.data!!
        }

    }

    fun getProducts(
        pageIndex: Int,
        pageSize: Int,
        onResponse: (response: ServiceResponse<List<Product>>) -> Unit,
    ) {
        viewModelScope.launch {
            var response = repository.getProducts(pageIndex, pageSize)
            onResponse(response)
        }
    }

    fun getNewProducts(onResponse: (response: ServiceResponse<List<Product>>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getNewProducts()
            if (response.status == "OK")
                dataList.value = response.data!!
            onResponse(response)
        }
    }

    fun getPopularProducts(onResponse: (response: ServiceResponse<List<Product>>) -> Unit) {
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