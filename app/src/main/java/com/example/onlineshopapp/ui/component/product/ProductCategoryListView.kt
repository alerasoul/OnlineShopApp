package com.example.onlineshopapp.ui.component.product

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopapp.ui.component.LoadingInRow
import com.example.onlineshopapp.viewmodel.product.ProductCategoryViewModel

@Composable
fun ProductCategoryListView(
    navController: NavController,
    viewModel: ProductCategoryViewModel = hiltViewModel(),
    ) {

    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    if (isLoading.value)
        LoadingInRow(modifier = Modifier
            .width(140.dp)
            .height(200.dp), 3)
    else {
        LazyRow {
            items(dataList.value.size) { index ->
                ProductCategoryItemView(dataList.value[index], navController)
            }
        }
    }
}