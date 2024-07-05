package com.example.genericapp.feature_photo.presentation.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PhotoListItem(title: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(8.dp) // padding inside the border
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}