package com.example.onlineshopapp.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp.ui.component.LoadingInColumn
import com.example.onlineshopapp.ui.component.product.ProductCategoryListView
import com.example.onlineshopapp.ui.component.product.ProductFilterView
import com.example.onlineshopapp.ui.component.product.ProductListItemView
import com.example.onlineshopapp.ui.component.slider.SliderListView
import com.example.onlineshopapp.viewmodel.product.ProductViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    productViewModel: ProductViewModel = hiltViewModel(),
) {

    var productList by remember { mutableStateOf(productViewModel.dataList) }
    var productLoading by remember { mutableStateOf(productViewModel.isLoading) }

    LazyColumn(
        modifier = Modifier
            .padding(20.dp, 0.dp)
    ) {
        item {
            SliderListView()
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            ProductCategoryListView(navController)
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            ProductFilterView()
            Spacer(modifier = Modifier.height(20.dp))
        }

        if (productLoading.value) {
            item {
                LoadingInColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        } else {
            items(productList.value.size) { index ->
                ProductListItemView(productList.value[index], navController)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

    }
}
