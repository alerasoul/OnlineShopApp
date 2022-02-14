package com.example.onlineshopapp.db.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.onlineshopapp.db.model.BasketEntity
import com.example.onlineshopapp.db.repository.BasketEntityRepository

class BasketEntityViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = BasketEntityRepository(application)

    //    var dataList = repository.getBasketListLive()
    var dataList = mutableStateOf<List<BasketEntity>>(listOf())

    private suspend fun insert(basketEntity: BasketEntity) {
        repository.insert(basketEntity)
    }

    suspend fun addToBasket(basketEntity: BasketEntity) {
        if (dataList.value.any { x ->
                x.productId == basketEntity.productId &&
                        x.colorId == basketEntity.colorId &&
                        x.sizeId == basketEntity.sizeId
            }) {
            val oldBasket =
                dataList.value!!.first { x ->
                    x.productId == basketEntity.productId
                            && x.colorId == basketEntity.colorId
                            && x.sizeId == basketEntity.sizeId
                }
            oldBasket.quantity++
            update(oldBasket)
        } else {
            insert(basketEntity)
        }
    }

    suspend fun delete(basketEntity: BasketEntity) {
        if (basketEntity.id <= 0) return
        repository.delete(basketEntity)
    }


    suspend fun increment(basketEntity: BasketEntity) {
        if (basketEntity.id <= 0) return
        repository.increment(basketEntity)
    }


    suspend fun decrement(basketEntity: BasketEntity) {
        if (basketEntity.id <= 0) return
        repository.decrement(basketEntity)
    }

    suspend fun deleteAll() {
        repository.deleteAll()
    }

    suspend fun update(basketEntity: BasketEntity) {
        if (basketEntity.id <= 0) return
        repository.update(basketEntity)
    }

    suspend fun getBasketList(): List<BasketEntity> {
        return repository.getBasketList()
    }

    fun getBasketListLive(): LiveData<List<BasketEntity>> {
        return repository.getBasketListLive()
    }

}