package com.example.onlineshopapp.model.invoice

import com.example.onlineshopapp.model.product.Product

data class InvoiceItem(
    var id: Int?,
    var product: Product?,
    var quantity: Int?,
    var unitPrice: Int?,
)