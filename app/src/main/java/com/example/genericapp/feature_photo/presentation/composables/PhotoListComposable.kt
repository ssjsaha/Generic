package com.example.genericapp.feature_photo.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.genericapp.R
import com.example.genericapp.feature_photo.presentation.ui_events.PhotoListPageUiEvent
import com.example.genericapp.feature_photo.presentation.ui_states.PhotoListUiState
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoListComposable(
    modifier: Modifier = Modifier,
    uiStateFlow: Flow<PhotoListUiState>,
    onEvent: (PhotoListPageUiEvent) -> Unit,
    onNavigate: () -> Unit
) {
    val snackbarHostState by remember {
        mutableStateOf(SnackbarHostState())
    }
    val pullToRefreshState = rememberPullToRefreshState()

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
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .nestedScroll(pullToRefreshState.nestedScrollConnection),

            ) {
            if (uiComposableState.loading) {
                ImageListShimmerLoader(modifier = Modifier)
            } else {
                if (uiComposableState.photoList.isNotEmpty()) {
                    LazyColumn {
                        items(
                            uiComposableState.photoList.size
                        ) {
                            PhotoListItem(title = uiComposableState.photoList[it].filename) {
                                onEvent(
                                    PhotoListPageUiEvent.NavigateToPhotoDetails(
                                        uiComposableState.photoList[it]
                                    )
                                )
                                onNavigate()
                            }
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        items(1) {
                            Text(
                                modifier = Modifier.padding(20.dp,0.dp,20.dp,0.dp),
                                text = uiComposableState.error,
                                textAlign = TextAlign.Center
                            )
                        }

                    }

                }
            }
            if (pullToRefreshState.isRefreshing) {
                LaunchedEffect(key1 = true) {
                    onEvent(PhotoListPageUiEvent.RefreshItems)
                }
            }
            LaunchedEffect(key1 = uiComposableState.isRefreshing) {
                if (uiComposableState.isRefreshing) {
                    pullToRefreshState.startRefresh()

                } else {
                    pullToRefreshState.endRefresh()
                }
            }
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullToRefreshState
            )

        }
    }
}