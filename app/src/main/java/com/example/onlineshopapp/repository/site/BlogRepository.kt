package com.example.onlineshopapp.repository.site

import com.example.onlineshopapp.api.site.BlogApi
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.site.Blog
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class BlogRepository @Inject constructor(private val api: BlogApi) {

    suspend fun getBlogs(): ServiceResponse<Blog> {
        return try {
            api.getBlogs()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getBlogById(id: Int): ServiceResponse<Blog> {
        return try {
            api.getBlogById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }


}