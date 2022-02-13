package com.example.onlineshopapp.ui.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp.ui.component.LoadingInColumn
import com.example.onlineshopapp.ui.component.LoadingInRow
import com.example.onlineshopapp.viewmodel.product.ProductViewModel
import com.skydoves.landscapist.glide.GlideImage
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowProductScreen(
    productId: Int,
    navController: NavHostController,
    productViewModel: ProductViewModel = hiltViewModel(),
) {
    var data by remember { mutableStateOf(productViewModel.data) }
    var isLoading by remember { mutableStateOf(true) }
    var selectedSize by remember { mutableStateOf(0) }
    var selectedColor by remember { mutableStateOf(0) }

    val context = LocalContext.current
    productViewModel.getProductById(productId) { response ->
        isLoading = false
        if (response.status == "OK") {
            if (response.data != null) {
                productViewModel.data.value = response.data[0]
            } else {
                Toast.makeText(context, "error on load data", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
        }
    }
    if (isLoading) {
        LoadingInColumn(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight())
    } else {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(0.dp)
        ) {

            Box {
                GlideImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    imageModel = data.value!!.image,
                    failure = {
                        Text(text = "image req failed")
                    }
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopStart
            ) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column() {

                    Text(
                        text = data.value!!.title!!,
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${data.value!!.price}T",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Sizes",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow {
                        items(data.value!!.size!!.size) { index ->
                            TextButton(
                                modifier = Modifier.size(35.dp),
                                onClick = { selectedSize = index },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = if (selectedSize == index) Color.White else Color.Transparent
                                )
                            ) {
                                Text(
                                    text = data.value!!.size!![index].title!!,
                                    fontWeight = FontWeight.Bold,
                                    color = if (selectedSize == index) Color.Black else Color.White
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Colors",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow {
                        items(data.value!!.colors!!.size) { index ->
                            TextButton(
                                modifier = Modifier.size(35.dp),
                                onClick = { selectedColor = index },
                                shape = RoundedCornerShape(50.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor =
                                    Color(android.graphics.Color.parseColor("#${data.value!!.colors!![index].hexValue}"))
                                ),
                                border = BorderStroke(1.dp, Color.White)
                            ) {
                                if (index == selectedColor)
                                    Icon(imageVector = Icons.Filled.Check,
                                        contentDescription = "",
                                        tint = if (data.value!!.colors!![index].hexValue!!.lowercase(
                                                Locale.getDefault()) == "000000"
                                        ) Color.White else Color.Black)
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Text(text = "Buy Now", fontWeight = FontWeight.Bold, fontSize = 17.sp)
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}
