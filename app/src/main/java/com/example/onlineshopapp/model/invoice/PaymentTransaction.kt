package com.example.onlineshopapp.model.invoice

import com.example.onlineshopapp.model.customer.UserVM

data class PaymentTransaction(
    var items: List<InvoiceItem>,
    var user: UserVM,
)