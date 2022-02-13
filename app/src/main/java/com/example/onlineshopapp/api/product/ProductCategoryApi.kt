package com.example.onlineshopapp.api.product

import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.product.ProductCategory
import retrofit2.http.GET
import retrofit2.http.Path


interface ProductCategoryApi {

    @GET("productCategory")
    suspend fun getProductCategories(): ServiceResponse<ProductCategory>

    @GET("productCategory/{id}")
    suspend fun getProductCategoryById(@Path("id") id: Int): ServiceResponse<ProductCategory>

}