package com.example.onlineshopapp.db.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.onlineshopapp.db.model.BasketEntity
import com.example.onlineshopapp.db.repository.BasketEntityRepository

class BasketEntityViewModel(application: Application) : AndroidViewModel(application) {
    var repository = BasketEntityRepository(application)
    var basketEntityList = repository.getBasketList()

    suspend fun insert(basketEntity: BasketEntity) {
        repository.insert(basketEntity)
    }

    suspend fun delete(basketEntity: BasketEntity) {
        if (basketEntity.id <= 0) return
        repository.delete(basketEntity)
    }

    suspend fun deleteAll() {
        repository.deleteAll()
    }

    suspend fun update(basketEntity: BasketEntity) {
        if (basketEntity.id <= 0) return
        repository.update(basketEntity)
    }

    suspend fun getBasketList(): LiveData<List<BasketEntity>> {
        return basketEntityList
    }

}