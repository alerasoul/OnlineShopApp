package com.example.onlineshopapp.repository.product

import com.example.onlineshopapp.api.product.ProductCategoryApi
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.product.ProductCategory
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ProductCategoryRepository @Inject constructor(private val api: ProductCategoryApi) {

    suspend fun getProductCategories(): ServiceResponse<List<ProductCategory>> {
        return try {
            api.getProductCategories()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getProductCategoryById(id: Int): ServiceResponse<ProductCategory> {
        return try {
            api.getProductCategoryById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }


}