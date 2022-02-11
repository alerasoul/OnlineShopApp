package com.example.onlineshopapp.viewmodel.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.site.Slider
import com.example.onlineshopapp.repository.site.SliderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor(private var repository: SliderRepository) : ViewModel() {

    fun getSliders(onResponse: (response: ServiceResponse<Slider>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getSliders()
            onResponse(response)
        }
    }

    fun getSliderById(id: Int, onResponse: (response: ServiceResponse<Slider>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getSliderById(id)
            onResponse(response)
        }
    }
}