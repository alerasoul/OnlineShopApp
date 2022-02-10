package com.example.onlineshopapp.api.site

import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.site.Blog
import retrofit2.http.GET
import retrofit2.http.Path


interface BlogApi {

    @GET("blog")
    suspend fun getBlogs(): ServiceResponse<Blog>

    @GET("blog/{id}")
    suspend fun getBlogById(@Path("id") id: Int): ServiceResponse<Blog>

}