package com.example.onlineshopapp.model

data class ServiceResponse<T>(
    val data: T? = null,
    val message: String? = null,
    val status: String? = null,
    val totalCount: Int? = null,
)