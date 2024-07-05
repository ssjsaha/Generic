package com.example.genericapp.feature_photo.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.SubcomposeAsyncImage
import com.example.genericapp.feature_photo.presentation.ui_states.PhotoDetailsUiState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun PhotoDetailsComposable(
    modifier: Modifier = Modifier,
    uiState: StateFlow<PhotoDetailsUiState>
) {
    val uiComposableState = uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (uiComposableState.value.isLandscape) {
            Arrangement.Center
        } else {
            Arrangement.Top
        }
    ) {
        SubcomposeAsyncImage(
            model = uiComposableState.value.photoUrl,
            contentDescription = "photo",
            loading = {
                CircularProgressIndicator()
            })
    }
}
