package com.example.onlineshopapp.api.customer

import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.customer.User
import com.example.onlineshopapp.model.customer.UserVM
import retrofit2.http.*

interface UserApi {

    @GET("user")
    suspend fun getUser(@Header("Authorization") token: String): ServiceResponse<User>

    @PUT("user/changePass")
    suspend fun changePassword(
        @Body user: UserVM,
        @Header("Authorization") token: String,
    ): ServiceResponse<User>

    @POST("user/login")
    suspend fun login(
        @Body user: UserVM,
    ): ServiceResponse<UserVM>

    @POST("user/register")
    suspend fun register(
        @Body user: UserVM,
    ): ServiceResponse<User>

    @PUT("user/update")
    suspend fun update(
        @Body user: UserVM,
        @Header("Authorization") token: String,
    ): ServiceResponse<User>


}