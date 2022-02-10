package com.example.onlineshopapp.model.customer

data class UserVM(
    var address: String?,
    var customerId: Long,
    var firstName: String?,
    var id: Int,
    var lastName: String?,
    var oldPassword: String?,
    var password: String?,
    var phone: String?,
    var postalCode: String?,
    var repeatPassword: String?,
    var token: String?,
    var username: String?,
)