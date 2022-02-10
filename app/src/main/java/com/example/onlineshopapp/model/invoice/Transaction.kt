package com.example.onlineshopapp.model.invoice

data class Transaction(
    var code: Int?,
    var invoiceNumber: Int?,
    var referenceId: String?,
    var status: String?,
)