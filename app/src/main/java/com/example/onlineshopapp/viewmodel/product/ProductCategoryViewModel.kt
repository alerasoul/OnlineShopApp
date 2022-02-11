package com.example.onlineshopapp.viewmodel.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.product.ProductCategory
import com.example.onlineshopapp.repository.product.ProductCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCategoryCategoryViewModel @Inject constructor(private var repository: ProductCategoryRepository) :
    ViewModel() {

    fun getProductCategories(onResponse: (response: ServiceResponse<ProductCategory>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getProductCategories()
            onResponse(response)
        }
    }

    fun getProductCategoryById(id: Int, onResponse: (response: ServiceResponse<ProductCategory>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getProductCategoryById(id)
            onResponse(response)
        }
    }
}