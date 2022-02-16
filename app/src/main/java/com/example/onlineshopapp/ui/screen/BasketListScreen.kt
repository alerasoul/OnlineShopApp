package com.example.onlineshopapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlineshopapp.db.viewmodel.BasketEntityViewModel
import com.example.onlineshopapp.ui.component.BasketListItemView

@Composable
fun BasketListScreen(navController: NavHostController, basketViewModel: BasketEntityViewModel) {
    val dataList by remember { mutableStateOf(basketViewModel.dataList) }
    var totalPrice: Long = 0
    LazyColumn {
        item {
            Row {
                IconButton(onClick = { navController.popBackStack() }, Modifier.width(60.dp)) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp, 60.dp, 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Shopping Card",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black)
                    if (dataList.value.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "${dataList.value.size} items",
                            fontSize = 16.sp,
                            color = Color.Gray)
                    }
                }
            }
        }

        item {
            if (dataList.value.isEmpty()) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "Your shopping card is empty!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize())
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        items(dataList.value.size) { index ->
            totalPrice += (dataList.value[index].quantity) * (dataList.value[index].price)
            BasketListItemView(dataList.value[index], basketViewModel, navController)
            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            if (dataList.value.isNotEmpty()) {
                Spacer(modifier = Modifier.height(2.dp))
                Row(Modifier.fillMaxWidth(), Arrangement.Center) {
                    Text(text = "Total price :", fontSize = 19.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "${totalPrice}T",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Normal, color = Color.DarkGray)
                }
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = {
                        navController.navigate("proceedToPayment")
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 0.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray
                    )
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically),
                        text = "Proceed To Payment",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.5.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

}
