package com.example.onlineshopapp.db.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.onlineshopapp.db.OnlineShopDatabase
import com.example.onlineshopapp.db.dao.UserEntityDao
import com.example.onlineshopapp.db.model.UserEntity


class UserEntityRepository(application: Application) {
    lateinit var userDao: UserEntityDao
    lateinit var currentUserEntity: LiveData<UserEntity>

    init {
        val database = OnlineShopDatabase.getInstance(application)
        userDao = database.getUserEntityDao()
        currentUserEntity = userDao.get()
    }

    fun getCurrentUser(): LiveData<UserEntity> {
        return currentUserEntity
    }

    suspend fun insert(userEntity: UserEntity) {
        deleteAll()
        userDao.addUser(userEntity)
    }

    suspend fun delete(userEntity: UserEntity) {
        userDao.delete(userEntity)
    }

    suspend fun deleteAll() {
        userDao.deleteAll()
    }

    suspend fun update(userEntity: UserEntity) {
        userDao.update(userEntity)
    }


}