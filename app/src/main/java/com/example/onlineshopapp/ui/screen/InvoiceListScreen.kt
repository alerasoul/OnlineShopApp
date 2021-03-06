package com.example.onlineshopapp.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopapp.ui.component.invoice.InvoiceListItemView
import com.example.onlineshopapp.ui.component.LoadingInColumn
import com.example.onlineshopapp.viewmodel.invoice.InvoiceViewModel

@Composable
fun InvoiceListScreen(
    navController: NavController,
    viewModel: InvoiceViewModel = hiltViewModel(),
) {
    val dataList by remember { mutableStateOf(viewModel.dataList) }
    val isLoading by remember { mutableStateOf(viewModel.isLoading) }

    val animatedVisibleState = remember { MutableTransitionState(false) }
        .apply { targetState = true }

    Column {
        AnimatedVisibility(
            visibleState = animatedVisibleState,
            enter = slideInVertically(
                animationSpec = tween(500),
                initialOffsetY = { -40 }
            ) + fadeIn(
                animationSpec = tween(500)
            ),
            exit = fadeOut()
        )
        {
            Row(Modifier.padding(20.dp)) {
                IconButton(onClick = { navController.popBackStack() }, Modifier.width(60.dp)) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 6.dp, 60.dp, 0.dp),
                    text = "Invoices",
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
            }
        }
        LazyColumn(
            modifier = Modifier.padding(20.dp, 0.dp)
        ) {
            if (isLoading.value) {
                item {
                    LoadingInColumn(modifier = Modifier
                        .fillMaxSize())
                }
            } else {
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
                        )
                        {
                        InvoiceListItemView(dataList.value[index], navController)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}