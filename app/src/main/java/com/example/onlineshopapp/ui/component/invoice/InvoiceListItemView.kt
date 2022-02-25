package com.example.onlineshopapp.ui.component.invoice

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.onlineshopapp.model.invoice.Invoice
import com.example.onlineshopapp.ui.component.AdvancedButton

@Composable
fun InvoiceListItemView(invoice: Invoice, navController: NavController) {
    AdvancedButton(invoice.addDate!!,
        invoice.status!!,
        if (invoice.status == "NotPayed") Icons.Filled.Close else Icons.Filled.Check,
        if (invoice.status == "NotPayed") Color.Red else Color.Green) {
        navController.navigate("invoice/${invoice.id}")
    }
}