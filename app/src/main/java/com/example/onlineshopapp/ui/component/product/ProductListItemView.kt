package com.example.onlineshopapp.ui.component.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlineshopapp.model.product.Product
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductListItemView(product: Product, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp), clip = true),
        shape = RoundedCornerShape(20.dp),
        onClick = {
            navController.navigate("showProduct/${product.id}")
        }
    ) {
        Box {
            GlideImage(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                imageModel = product.image!!,
                loading = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Column() {
                    Text(
                        text = product.title!!,
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${product.price.toString()}T",
                        color = Color.DarkGray,
                        fontSize = 18.sp
                    )
                }
            }
        }

    }
    Spacer(modifier = Modifier.width(20.dp))
}

