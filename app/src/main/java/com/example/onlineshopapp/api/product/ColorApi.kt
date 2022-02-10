package com.example.onlineshopapp.api.product

import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.product.ProductColor
import retrofit2.http.GET
import retrofit2.http.Path


interface ColorApi {

    @GET("color")
    suspend fun getColors(): ServiceResponse<ProductColor>

    @GET("color/{id}")
    suspend fun getColorById(@Path("id") id: Int): ServiceResponse<ProductColor>

}