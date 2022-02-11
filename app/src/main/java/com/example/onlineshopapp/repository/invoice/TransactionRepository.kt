package com.example.onlineshopapp.repository.invoice

import com.example.onlineshopapp.api.invoice.TransactionApi
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.invoice.PaymentTransaction
import com.example.onlineshopapp.model.invoice.Transaction
import com.example.onlineshopapp.repository.base.BaseRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class TransactionRepository @Inject constructor(private val api: TransactionApi) {

    suspend fun goToPayment(
        data: PaymentTransaction,
    ): ServiceResponse<String> {
        return try {
            api.goToPayment(data)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

}