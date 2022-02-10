package com.example.onlineshopapp.api.invoice

import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.invoice.Invoice
import retrofit2.http.*


interface InvoiceApi {

    @POST("invoice")
    suspend fun addInvoice(
        @Body invoice: Invoice,
        @Header("Authorization") token: String,
    ): ServiceResponse<Invoice>

    @GET("invoice/{id}")
    suspend fun getInvoiceById(
        @Path("id") id: Int,
        @Header("Authorization") token: String,
    ): ServiceResponse<Invoice>

    @GET("invoice/user/{userId}")
    suspend fun getInvoicesByUserId(
        @Path("userId") userId: Int,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int,
        @Header("Authorization") token: String,
    ): ServiceResponse<Invoice>

}