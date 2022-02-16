package com.example.onlineshopapp.model.customer

import com.example.onlineshopapp.db.model.UserEntity

data class UserVM(
    var address: String? = "",
    var customerId: Int? = null,
    var firstName: String? = "",
    var id: Int? = null,
    var lastName: String? = "",
    var oldPassword: String? = null,
    var password: String?,
    var phone: String? = "",
    var postalCode: String? = "",
    var repeatPassword: String? = null,
    var token: String? = null,
    var username: String?,
) {
    fun convertToUserEntity(): UserEntity {
        return UserEntity(
            userId = id!!,
            address = address!!,
            phone = phone!!,
            customerId = customerId!!,
            firstName = firstName!!,
            lastName = lastName!!,
            postalCode = postalCode!!,
            token = token!!,
            username = username,
        )
    }
}