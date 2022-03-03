package com.example.onlineshopapp.viewmodel.invoice

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.invoice.Invoice
import com.example.onlineshopapp.repository.invoice.InvoiceRepository
import com.example.onlineshopapp.util.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoiceItemViewModel @Inject constructor(private var repository: InvoiceRepository) :
    ViewModel() {

    var token: String = ThisApp.token
    var invoiceId: Int = ThisApp.invoiceId
    var data = mutableStateOf<Invoice?>(null)
    var isLoading = mutableStateOf(true)

    init {
        getInvoiceById(invoiceId) { response ->
            isLoading.value = false
            if (response.status == "OK") {
                data.value = response.data!![0]
            }
        }
    }

    private fun getInvoiceById(id: Int, onResponse: (response: ServiceResponse<Invoice>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getInvoiceById(id, token)
            onResponse(response)
        }
    }
}