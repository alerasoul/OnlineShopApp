package com.example.onlineshopapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopapp.ui.component.LoadingInColumn
import com.example.onlineshopapp.ui.theme.LightGreen
import com.example.onlineshopapp.ui.theme.LightRed
import com.example.onlineshopapp.viewmodel.invoice.InvoiceItemViewModel

@Composable
fun InvoiceScreen(
    navController: NavController,
    id: Int?,
    viewModel: InvoiceItemViewModel = hiltViewModel(),
) {
    val data by remember { mutableStateOf(viewModel.data) }
    val isLoading by remember { mutableStateOf(viewModel.isLoading) }

    if (isLoading.value || data.value == null) {
        LoadingInColumn(modifier = Modifier
            .fillMaxSize())
    } else {
        Column {
            Row(Modifier.padding(20.dp)) {
                IconButton(onClick = { navController.popBackStack() }, Modifier.width(60.dp)) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 6.dp, 60.dp, 0.dp),
                    text = "Invoice",
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .padding(30.dp),
                horizontalAlignment = CenterHorizontally
            ) {
                Card(modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(20.dp)
                    .align(CenterHorizontally),
                    shape = RoundedCornerShape(20.dp),
                    backgroundColor = if (data.value!!.status == "NotPayed") LightRed else LightGreen) {
                    Icon(modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                        imageVector = if (data.value!!.status == "NotPayed") Icons.Filled.Close else Icons.Filled.Check,
                        tint = Color.White,
                        contentDescription = "")
                }
            }
            Column(Modifier
                .fillMaxSize()
                .padding(20.dp)) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(text = "Status: ${data.value!!.status!!}", fontSize = 22.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Add date: ${data.value!!.addDate!!}", fontSize = 22.sp)
                if (!data.value!!.paymentDate!!.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Payment date: ${data.value!!.paymentDate!!}", fontSize = 22.sp)
                }
            }
        }
    }
}