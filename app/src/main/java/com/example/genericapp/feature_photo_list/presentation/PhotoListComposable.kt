package com.example.genericapp.feature_photo_list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.genericapp.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoListComposable(
    modifier: Modifier = Modifier,
    uiStateFlow: Flow<PhotoListUiState>,
    onEvent: (PhotoListPageUiEvent) -> Unit
) {
    val snackbarHostState by remember {
        mutableStateOf(SnackbarHostState())
    }
    val uiComposableState = uiStateFlow.collectAsState(initial = PhotoListUiState()).value
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.app_name))
                },
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (uiComposableState.loading) {
                CircularProgressIndicator(modifier = Modifier)
            } else {
                if (uiComposableState.error.isNotEmpty()) {
                    Text(text = uiComposableState.error)
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(
                            uiComposableState.photoList.size
                        ) {
                            PhotoListItem(title = uiComposableState.photoList[it].filename) {
                                onEvent(
                                    PhotoListPageUiEvent.NavigateToPhotoDetails(
                                        uiComposableState.photoList[it]
                                    )
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}