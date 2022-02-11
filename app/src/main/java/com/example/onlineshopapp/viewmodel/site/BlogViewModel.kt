package com.example.onlineshopapp.viewmodel.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.site.Blog
import com.example.onlineshopapp.repository.site.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(private var repository: BlogRepository) : ViewModel() {

    fun getBlogs(onResponse: (response: ServiceResponse<Blog>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getBlogs()
            onResponse(response)
        }
    }

    fun getBlogById(id: Int, onResponse: (response: ServiceResponse<Blog>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getBlogById(id)
            onResponse(response)
        }
    }
}