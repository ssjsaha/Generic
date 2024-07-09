package com.example.genericapp.feature_photo.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.SubcomposeAsyncImage
import com.example.genericapp.feature_photo.presentation.ui_models.ImageLoadVerdict
import com.example.genericapp.feature_photo.presentation.ui_states.PhotoDetailsUiState
import kotlinx.coroutines.flow.StateFlow
import com.example.genericapp.R
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoDetailsComposable(
    modifier: Modifier = Modifier,
    uiState: StateFlow<PhotoDetailsUiState>,
    onBackClick: () -> Unit
) {
    val uiComposableState = uiState.collectAsState()
    var imageLoadVerdict by remember {
        mutableStateOf(ImageLoadVerdict.LOADING)
    }
    val snackbarHostState by remember {
        mutableStateOf(SnackbarHostState())
    }

    Scaffold(

        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Text(
                        stringResource(id = R.string.details),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
            )
        }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState()),
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
                    imageLoadVerdict = ImageLoadVerdict.LOADING
                },
                onError = {
                    imageLoadVerdict = ImageLoadVerdict.ERROR

                },
                onSuccess = {
                    imageLoadVerdict = ImageLoadVerdict.SUCCESS
                }
            )
            if (imageLoadVerdict == ImageLoadVerdict.SUCCESS) {
                uiComposableState.value.photo?.author?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 20.dp, 20.dp, 20.dp)
                    )
                }

            }

        }
        if (imageLoadVerdict == ImageLoadVerdict.LOADING) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ImageDetailsShimmerLoader()
            }
        } else if (imageLoadVerdict == ImageLoadVerdict.ERROR) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(painter = painterResource(id = R.drawable.error), contentDescription = null)
            }
        }
    }

}
