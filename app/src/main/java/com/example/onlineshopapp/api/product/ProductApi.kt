package com.example.onlineshopapp.api.product

import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.product.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ProductApi {

    @GET("product")
    suspend fun getProducts(
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int,
    ): ServiceResponse<Product>

    @GET("product/{id}")
    suspend fun getProductById(@Path("id") id: Int): ServiceResponse<Product>

    @GET("product/new")
    suspend fun getNewProducts(): ServiceResponse<Product>

    @GET("product/popular")
    suspend fun getPopularProducts(): ServiceResponse<Product>

}