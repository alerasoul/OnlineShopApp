package com.example.onlineshopapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onlineshopapp.db.dao.BasketEntityDao
import com.example.onlineshopapp.db.dao.UserEntityDao
import com.example.onlineshopapp.db.model.BasketEntity
import com.example.onlineshopapp.db.model.UserEntity

@Database(entities = [UserEntity::class, BasketEntity::class], version = 6)
abstract class OnlineShopDatabase : RoomDatabase() {

    abstract fun getUserEntityDao(): UserEntityDao
    abstract fun getBasketEntityDao(): BasketEntityDao

    companion object {
        var instance: OnlineShopDatabase? = null
        fun getInstance(context: Context): OnlineShopDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context,
                    OnlineShopDatabase::class.java,
                    "onlineShop-database").fallbackToDestructiveMigration().build()
            }
            return instance as OnlineShopDatabase
        }
    }
}