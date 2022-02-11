package com.example.onlineshopapp.repository.customer

import com.example.onlineshopapp.api.customer.UserApi
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.customer.User
import com.example.onlineshopapp.model.customer.UserVM
import com.example.onlineshopapp.repository.base.BaseRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UserRepository @Inject constructor(private val api: UserApi) : BaseRepository() {

    suspend fun getUser(token: String): ServiceResponse<User> {
        return try {
            api.getUser(prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun changePass(
        data: UserVM,
        token: String,
    ): ServiceResponse<User> {
        return try {
            api.changePassword(data, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun login(
        data: UserVM,
    ): ServiceResponse<UserVM> {
        return try {
            api.login(data)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun register(
        data: UserVM,
    ): ServiceResponse<User> {
        return try {
            api.register(data)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun update(
        data: UserVM,
        token: String,
    ): ServiceResponse<User> {
        return try {
            api.update(data, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

}