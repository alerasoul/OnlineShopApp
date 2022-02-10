package com.example.onlineshopapp.model.invoice

import com.example.onlineshopapp.model.customer.User

data class Invoice(
    var addDate: String?,
    var id: Int?,
    var invoiceItems: List<InvoiceItem>?,
    var paymentDate: String?,
    var status: String?,
    var transactions: Transaction?,
    var user: User?,
)