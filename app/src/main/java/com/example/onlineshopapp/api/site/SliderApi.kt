package com.example.onlineshopapp.api.site

import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.site.Slider
import retrofit2.http.GET
import retrofit2.http.Path


interface SliderApi {

    @GET("slider")
    suspend fun getSliders(): ServiceResponse<Slider>

    @GET("slider/{id}")
    suspend fun getSliderById(@Path("id") id: Int): ServiceResponse<Slider>

}