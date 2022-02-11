package com.example.onlineshopapp.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BasketEntity(
    @PrimaryKey
    val id: Int = 0,
    var productId: Int?,
    var quantity: Int?,
)