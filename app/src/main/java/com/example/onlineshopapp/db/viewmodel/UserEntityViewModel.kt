package com.example.onlineshopapp.db.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.onlineshopapp.db.model.UserEntity
import com.example.onlineshopapp.db.repository.UserEntityRepository

class UserEntityViewModel(application: Application) : AndroidViewModel(application) {

    var repository = UserEntityRepository(application)

    var currentUserEntity = mutableStateOf<UserEntity?>(null)

    suspend fun insert(userEntity: UserEntity) {
        repository.insert(userEntity)
    }

    suspend fun delete(userEntity: UserEntity) {
        if (userEntity.id <= 0) return
        repository.delete(userEntity)
    }

    suspend fun deleteAll() {
        repository.deleteAll()
    }

    suspend fun update(userEntity: UserEntity) {
        if (userEntity.id <= 0) return
        repository.update(userEntity)
    }

    fun getCurrentUser(): LiveData<UserEntity> {
        return repository.getCurrentUser()
    }

    fun isLoggedIn(): Boolean {
        return currentUserEntity.value != null
    }
}