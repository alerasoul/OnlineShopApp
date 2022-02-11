package com.example.onlineshopapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.onlineshopapp.db.model.BasketEntity

@Dao
interface BasketEntityDao {

    @Insert
    fun addBasket(userEntity: BasketEntity)

    @Delete
    fun delete(userEntity: BasketEntity)

    @Update
    fun update(userEntity: BasketEntity)

    @Query("select * from BasketEntity")
    fun getAll(): LiveData<List<BasketEntity>>

    @Query("delete from BasketEntity")
    fun deleteAll()

}