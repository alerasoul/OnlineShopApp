package com.example.onlineshopapp.repository.invoice

import com.example.onlineshopapp.api.invoice.InvoiceApi
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.invoice.Invoice
import com.example.onlineshopapp.repository.base.BaseRepository
import javax.inject.Inject

class InvoiceRepository @Inject constructor(private val api: InvoiceApi) : BaseRepository() {

    suspend fun getInvoiceById(id: Int, token: String): ServiceResponse<Invoice> {
        return try {
            api.getInvoiceById(id, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getInvoiceByUserId(
        userId: Int,
        pageIndex: Int,
        pageSize: Int,
        token: String,
    ): ServiceResponse<Invoice> {
        return try {
            api.getInvoicesByUserId(userId, pageIndex, pageSize, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }


    suspend fun addInvoice(
        data: Invoice,
        token: String,
    ): ServiceResponse<Invoice> {
        return try {
            api.addInvoice(data, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }


}