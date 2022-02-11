package com.example.onlineshopapp.repository.product

import com.example.onlineshopapp.api.product.ColorApi
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.product.ProductColor
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ColorRepository @Inject constructor(private val api: ColorApi) {

    suspend fun getColors(): ServiceResponse<ProductColor> {
        return try {
            api.getColors()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getColorById(id: Int): ServiceResponse<ProductColor> {
        return try {
            api.getColorById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }


}