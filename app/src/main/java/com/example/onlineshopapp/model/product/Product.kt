package com.example.onlineshopapp.model.product

data class Product(
    var addDate: String? = "",
    var category: ProductCategory? = null,
    var colors: List<ProductColor>? = null,
    var description: String? = "",
    var id: Int? = 0,
    var image: String? = "",
    var price: Int? = 0,
    var size: List<ProductSize>? = null,
    var title: String? = "",
    var visitCount: Int? = 0,
)
