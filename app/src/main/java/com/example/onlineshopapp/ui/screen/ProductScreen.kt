package com.example.onlineshopapp.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp.ui.component.LoadingInColumn
import com.example.onlineshopapp.ui.component.product.ProductListItemView
import com.example.onlineshopapp.viewmodel.product.ProductByCategoryViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductScreen(
    categoryId: Int,
    categoryTitle: String,
    navController: NavHostController,
    viewModel: ProductByCategoryViewModel = hiltViewModel(),
) {
    val dataList by remember { mutableStateOf(viewModel.dataList) }
    val isLoading by remember { mutableStateOf(viewModel.isLoading) }

    val animatedVisibleState = remember { MutableTransitionState(false) }
        .apply { targetState = true }

    Column {
        LazyColumn(
            modifier = Modifier.padding(20.dp, 0.dp)
        ) {
            item {
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500),
                        initialOffsetY = { -40 }
                    ) + fadeIn(
                        animationSpec = tween(500)
                    ),
                    exit = fadeOut()
                ) {
                    Text(text = categoryTitle, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(dataList.value.size) { index ->
                viewModel.onScrollPositionChange(index)
                if ((index + 1) >= (viewModel.pageIndex.value * viewModel.pageSize) && !viewModel.isLoading.value) {
                    viewModel.nextPage()
                }
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500, 500),
                        initialOffsetY = { -40 }
                    ) + fadeIn(
                        animationSpec = tween(500, 500)
                    ),
                    exit = fadeOut()
                ) {
                    ProductListItemView(product = dataList.value[index],
                        navController = navController)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            if (isLoading.value) {
                item {
                    LoadingInColumn(modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp))
                }
            }
        }

    }
}