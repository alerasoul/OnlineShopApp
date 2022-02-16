package com.example.onlineshopapp.model.invoice

import com.example.onlineshopapp.db.model.BasketEntity
import com.example.onlineshopapp.model.product.Product

data class InvoiceItem(
    var id: Int? = null,
    var product: Product?,
    var quantity: Int?,
    var unitPrice: Int? = 0,
) {
    companion object{
        fun convertFromBasket(basketEntity: BasketEntity): InvoiceItem {
            return InvoiceItem(
                product = Product(id = basketEntity.productId),
                quantity = basketEntity.quantity
            )
        }
    }
}