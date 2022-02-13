package com.example.onlineshopapp.viewmodel.product

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.product.Product
import com.example.onlineshopapp.model.product.ProductCategory
import com.example.onlineshopapp.model.site.Slider
import com.example.onlineshopapp.repository.product.ProductRepository
import com.example.onlineshopapp.util.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductByCategoryViewModel @Inject constructor(private var repository: ProductRepository) :
    ViewModel() {

    var categoryId = ThisApp.categoryId
    var pageSize = 3
    var pageIndex = mutableStateOf(0)
    var dataList = mutableStateOf<List<Product>>(listOf())
    var isLoading = mutableStateOf(true)
    private var scrollPosition = 0

    init {
        getAllByCategoryId(categoryId, pageIndex.value, pageSize) { response ->
            isLoading.value = false
            if (response.status == "OK")
                dataList.value = response.data!!
        }
    }

    fun getAllByCategoryId(
        categoryId: Int,
        pageIndex: Int,
        pageSize: Int,
        onResponse: (response: ServiceResponse<Product>) -> Unit,
    ) {
        viewModelScope.launch {
            val response = repository.getAllByCategoryId(categoryId, pageIndex, pageSize)
            onResponse(response)
        }
    }

    fun incrementIndex() {
        this.pageIndex.value = pageIndex.value + 1
    }

    fun onScrollPositionChange(position: Int) {
        scrollPosition = position
    }

    fun appendItems(items: List<Product>) {
        var current = ArrayList(dataList.value)
        current.addAll(items)
        dataList.value = current
    }

    fun nextPage() {
        if ((scrollPosition + 1) >= pageIndex.value * pageSize) {
            isLoading.value = true
            incrementIndex()
            if (pageIndex.value > 0) {
                getAllByCategoryId(categoryId, pageIndex.value, pageSize) { response ->
                    if (response.status == "OK" && response.data!!.isNotEmpty())
                        appendItems(response.data!!)
                }
                isLoading.value = false
            }
        }
    }
}