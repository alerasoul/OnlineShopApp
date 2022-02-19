package com.example.onlineshopapp.ui.component

import android.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlineshopapp.ui.theme.Light1

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdvancedButton(
    title: String,
    subtitle: String,
    imageVector: ImageVector,
    iconBackColor: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit,
) {
    Card(onClick = { onClick }) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Card(modifier = Modifier
                .width(50.dp)
                .height(50.dp),
                backgroundColor = iconBackColor, shape = RoundedCornerShape(50.dp)) {
                Icon(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    imageVector = imageVector, tint = androidx.compose.ui.graphics.Color.White,
                    contentDescription = "")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    color = androidx.compose.ui.graphics.Color.Black,
                )
                Text(
                    text = subtitle,
                    fontSize = 18.sp,
                    color = androidx.compose.ui.graphics.Color.Gray,
                )
            }
        }
    }
}