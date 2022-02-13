package com.example.onlineshopapp.viewmodel.product

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.product.ProductCategory
import com.example.onlineshopapp.repository.product.ProductCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCategoryViewModel @Inject constructor(private var repository: ProductCategoryRepository) :
    ViewModel() {

    var dataList = mutableStateOf<List<ProductCategory>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getProductCategories { response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

    fun getProductCategories(onResponse: (response: ServiceResponse<ProductCategory>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getProductCategories()
            onResponse(response)
        }
    }

    fun getProductCategoryById(
        id: Int,
        onResponse: (response: ServiceResponse<ProductCategory>) -> Unit,
    ) {
        viewModelScope.launch {
            var response = repository.getProductCategoryById(id)
            onResponse(response)
        }
    }
}