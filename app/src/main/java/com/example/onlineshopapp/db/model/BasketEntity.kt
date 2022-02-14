package com.example.onlineshopapp.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BasketEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var productId: Int,
    var quantity: Int,
    var colorId: Int,
    var sizeId: Int,
    var image: String,
    var price: Long,
    var title: String,
    var hexColor: String,
    var sizeTitle: String,
)