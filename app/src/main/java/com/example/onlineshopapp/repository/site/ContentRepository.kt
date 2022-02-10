package com.example.onlineshopapp.repository.site

import com.example.onlineshopapp.api.site.ContentApi
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.site.Content
import javax.inject.Inject

class ContentRepository @Inject constructor(private val api: ContentApi) {

    suspend fun getContents(): ServiceResponse<Content> {
        return try {
            api.getContents()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getContentByTitle(title: String): ServiceResponse<Content> {
        return try {
            api.getContentByTitle(title)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }


}