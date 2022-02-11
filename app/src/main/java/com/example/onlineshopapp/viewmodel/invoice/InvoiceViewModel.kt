package com.example.onlineshopapp.viewmodel.invoice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.model.ServiceResponse
import com.example.onlineshopapp.model.invoice.Invoice
import com.example.onlineshopapp.repository.invoice.InvoiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(private var repository: InvoiceRepository) :
    ViewModel() {

    fun getInvoiceById(id: Int, onResponse: (response: ServiceResponse<Invoice>) -> Unit) {
        viewModelScope.launch {
            //ToDo:FixMe:TOKEN
            var response = repository.getInvoiceById(id, "")
            onResponse(response)
        }
    }

    fun getInvoiceByUserId(
        userId: Int,
        pageIndex: Int,
        pageSize: Int,
        onResponse: (response: ServiceResponse<Invoice>) -> Unit,
    ) {
        viewModelScope.launch {
            //ToDo:FixMe:TOKEN
            var response = repository.getInvoiceByUserId(userId, pageIndex, pageSize, "")
            onResponse(response)
        }
    }

    fun addInvoice(
        data: Invoice,
        onResponse: (response: ServiceResponse<Invoice>) -> Unit,
    ) {
        viewModelScope.launch {
            //ToDo:FixMe:TOKEN
            var response = repository.addInvoice(data, "")
            onResponse(response)
        }
    }

}