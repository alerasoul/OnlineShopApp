package com.example.onlineshopapp.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlineshopapp.R
import com.example.onlineshopapp.db.model.BasketEntity
import com.example.onlineshopapp.db.viewmodel.BasketEntityViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasketListItemView(
    basketEntity: BasketEntity,
    basketEntityViewModel: BasketEntityViewModel,
    navController: NavController,
) {
    var quantity by remember { mutableStateOf(basketEntity.quantity) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp)
            .height(100.dp)
    ) {
        Card(
            modifier = Modifier
                .size(100.dp)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp), clip = true),
            shape = RoundedCornerShape(10.dp), onClick = {
                navController.navigate("showProduct/${basketEntity.productId}")
            }
        ) {
            GlideImage(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                imageModel = basketEntity.image,
                loading = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                },
                failure = {
                    Text(text = "image req failed")
                }
            )
        }

        Column(
            Modifier
                .height(100.dp)
                .align(Alignment.CenterVertically)) {
            Text(text = basketEntity.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(18.dp, 0.dp))
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "${basketEntity.price}T",
                fontSize = 16.5.sp,
                color = Color.Gray,
                modifier = Modifier.padding(18.dp, 0.dp))
            Row(Modifier.padding(0.dp)) {
                IconButton(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            basketEntityViewModel.decrement(basketEntity)
                        }
                        quantity--
                    }
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_baseline_remove_circle_24),
                        contentDescription = "",
                        Modifier.size(19.dp), tint = Color.DarkGray)
                }
                Text(modifier = Modifier.align(Alignment.CenterVertically),
                    text = quantity.toString(),
                    fontSize = 18.sp, color = Color.DarkGray)
                IconButton(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            basketEntityViewModel.increment(basketEntity)
                        }
                        quantity++
                    }) {
                    Icon(imageVector = Icons.Filled.AddCircle,
                        contentDescription = "",
                        Modifier.size(19.dp), tint = Color.DarkGray)
                }
                IconButton(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            basketEntityViewModel.delete(basketEntity)
                        }
                    }) {
                    Icon(imageVector = Icons.Filled.Delete,
                        contentDescription = "",
                        Modifier.size(21.dp),
                        tint = Color.Red)
                }
            }
        }
        Spacer(modifier = Modifier.width(40.dp))
        Card(
            shape = RoundedCornerShape(50.dp),
            backgroundColor =
            Color(android.graphics.Color.parseColor("#${basketEntity.hexColor}")),
            modifier = Modifier
                .size(50.dp),
            border = BorderStroke(1.dp, Color.LightGray),
        ) {
            Box(Modifier.fillMaxHeight(), contentAlignment = Center) {
                Text(
                    text = basketEntity.sizeTitle,
                    fontWeight = FontWeight.Bold,
                    color = if (basketEntity.hexColor == "000000") Color.White else Color.Black,
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
