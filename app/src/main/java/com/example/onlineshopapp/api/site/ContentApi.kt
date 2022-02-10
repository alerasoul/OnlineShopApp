package com.example.onlineshopapp.api.site

import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.site.Content
import retrofit2.http.GET
import retrofit2.http.Path


interface ContentApi {

    @GET("content")
    suspend fun getContents(): ServiceResponse<Content>

    @GET("content/{title}")
    suspend fun getContentByTitle(@Path("title") title: String): ServiceResponse<Content>

}