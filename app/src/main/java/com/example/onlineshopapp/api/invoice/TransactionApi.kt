package com.example.onlineshopapp.api.invoice

import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.invoice.Invoice
import com.example.onlineshopapp.model.invoice.PaymentTransaction
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface TransactionApi {

    @POST("trx/gotoPayment")
    suspend fun goToPayment(@Body data: PaymentTransaction): ServiceResponse<String>
}