package com.example.genericapp.feature_photo.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ImageListShimmerLoader(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(20) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .height(30.dp)
                            .shimmerEffect()
                    )
                }
            }
        }
    }
}
