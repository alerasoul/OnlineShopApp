package com.example.onlineshopapp.model.product

data class Product(
    var addDate: String?,
    var category: ProductCategory?,
    var colors: ProductColor?,
    var description: String?,
    var id: Int?,
    var image: String?,
    var price: Int?,
    var size: List<ProductSize>?,
    var title: String?,
    var visitCount: Int?,
)
