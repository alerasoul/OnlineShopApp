package com.example.onlineshopapp.viewmodel.invoice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.invoice.PaymentTransaction
import com.example.onlineshopapp.repository.invoice.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private var repository: TransactionRepository) :
    ViewModel() {

    fun goToPayment(
        data: PaymentTransaction,
        onResponse: (response: ServiceResponse<String>) -> Unit,
    ) {
        viewModelScope.launch {
            val response = repository.goToPayment(data)
            onResponse(response)
        }
    }

}