package com.example.onlineshopapp.repository.site

import com.example.onlineshopapp.api.site.SliderApi
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.site.Slider
import javax.inject.Inject

class SliderRepository @Inject constructor(private val api: SliderApi) {

    suspend fun getSliders(): ServiceResponse<Slider> {
        return try {
            api.getSliders()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getSliderById(id:Int): ServiceResponse<Slider> {
        return try {
            api.getSliderById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }


}