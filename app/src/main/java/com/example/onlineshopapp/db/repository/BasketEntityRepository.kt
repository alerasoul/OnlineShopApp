package com.example.onlineshopapp.db.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.onlineshopapp.db.OnlineShopDatabase
import com.example.onlineshopapp.db.dao.BasketEntityDao
import com.example.onlineshopapp.db.model.BasketEntity

class BasketEntityRepository(application: Application) {
    lateinit var basketDao: BasketEntityDao
    lateinit var basketEntityList: LiveData<List<BasketEntity>>

    init {
        val database = OnlineShopDatabase.getInstance(application)
        basketDao = database.getBasketEntityDao()
        basketEntityList = basketDao.getAll()
    }

    fun getBasketList(): LiveData<List<BasketEntity>> {
        return basketEntityList
    }

    suspend fun insert(basketEntity: BasketEntity) {
        deleteAll()
        basketDao.addBasket(basketEntity)
    }

    suspend fun delete(basketEntity: BasketEntity) {
        basketDao.delete(basketEntity)
    }

    suspend fun deleteAll() {
        basketDao.deleteAll()
    }

    suspend fun update(basketEntity: BasketEntity) {
        basketDao.update(basketEntity)
    }


}