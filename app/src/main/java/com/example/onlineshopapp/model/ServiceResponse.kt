package com.example.onlineshopapp.model

data class ServiceResponse<T>(
    val data: List<T>? = null,
    val message: String? = null,
    val status: String? = null,
    val totalCount: Int? = null,
)