package com.example.onlineshopapp.viewmodel.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.site.Content
import com.example.onlineshopapp.repository.site.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(private var repository: ContentRepository) :
    ViewModel() {

    fun getContents(onResponse: (response: ServiceResponse<Content>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getContents()
            onResponse(response)
        }
    }

    fun getContentByTitle(title: String, onResponse: (response: ServiceResponse<Content>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getContentByTitle(title)
            onResponse(response)
        }
    }
}