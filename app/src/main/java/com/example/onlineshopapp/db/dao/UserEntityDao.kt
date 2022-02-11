package com.example.onlineshopapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.onlineshopapp.db.model.UserEntity

@Dao
interface UserEntityDao {

    @Insert
    fun addUser(userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

    @Update
    fun update(userEntity: UserEntity)

    @Query("select * from UserEntity limit 1")
    fun get(): LiveData<UserEntity>

    @Query("delete from UserEntity")
    fun deleteAll()

}