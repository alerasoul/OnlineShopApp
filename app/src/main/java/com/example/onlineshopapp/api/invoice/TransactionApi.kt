package com.example.onlineshopapp.api.invoice

import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.invoice.PaymentTransaction
import retrofit2.http.Body
import retrofit2.http.POST


interface TransactionApi {

    @POST("trx/goToPayment")
    suspend fun goToPayment(@Body data: PaymentTransaction): ServiceResponse<String>
}