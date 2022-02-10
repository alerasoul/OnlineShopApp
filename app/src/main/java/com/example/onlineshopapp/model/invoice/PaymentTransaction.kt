package com.example.onlineshopapp.model.invoice

import com.example.onlineshopapp.model.customer.User

data class PaymentTransaction(
    var items: List<InvoiceItem>?,
    var user: User?,
)