package com.example.onlineshopapp.db.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.onlineshopapp.db.OnlineShopDatabase
import com.example.onlineshopapp.db.dao.BasketEntityDao
import com.example.onlineshopapp.db.model.BasketEntity

class BasketEntityRepository(application: Application) {
    private lateinit var basketDao: BasketEntityDao
    private var liveDataBasket: LiveData<List<BasketEntity>>

    init {
        val database = OnlineShopDatabase.getInstance(application)
        basketDao = database.getBasketEntityDao()
        liveDataBasket = basketDao.getAllLive()
    }

    suspend fun getBasketList(): List<BasketEntity> {
        return basketDao.getAll()
    }

    fun getBasketListLive(): LiveData<List<BasketEntity>> {
        return liveDataBasket
    }

    suspend fun insert(basketEntity: BasketEntity) {
        basketDao.addBasket(basketEntity)
    }

    suspend fun delete(basketEntity: BasketEntity) {
        basketDao.delete(basketEntity)
    }

    suspend fun increment(basketEntity: BasketEntity) {
        basketEntity.quantity++
        basketDao.update(basketEntity)
    }

    suspend fun decrement(basketEntity: BasketEntity) {
        basketEntity.quantity--
        if (basketEntity.quantity <= 0)
            delete(basketEntity)
        else
            basketDao.update(basketEntity)
    }

    suspend fun deleteAll() {
        basketDao.deleteAll()
    }

    suspend fun update(basketEntity: BasketEntity) {
        basketDao.update(basketEntity)
    }


}