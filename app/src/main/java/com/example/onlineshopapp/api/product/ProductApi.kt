package com.example.onlineshopapp.api.product

import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.product.Product
import com.example.onlineshopapp.model.product.ProductColor
import retrofit2.http.GET
import retrofit2.http.Path


interface ProductApi {

    @GET("product")
    suspend fun getProducts(): ServiceResponse<Product>

    @GET("product/{id}")
    suspend fun getProductById(@Path("pageIndex") pageIndex: Int): ServiceResponse<Product>

    @GET("product/new")
    suspend fun getNewProducts(): ServiceResponse<Product>

    @GET("product/popular")
    suspend fun getPopularProducts(): ServiceResponse<Product>

}