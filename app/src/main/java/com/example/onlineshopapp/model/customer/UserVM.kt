package com.example.onlineshopapp.model.customer

data class UserVM(
    var address: String?,
    var customerId: Int? = null,
    var firstName: String?,
    var id: Int? = null,
    var lastName: String?,
    var oldPassword: String? = null,
    var password: String?,
    var phone: String?,
    var postalCode: String?,
    var repeatPassword: String? = null,
    var token: String? = null,
    var username: String?,
)