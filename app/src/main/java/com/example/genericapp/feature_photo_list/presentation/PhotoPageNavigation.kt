package com.example.genericapp.feature_photo_list.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation


@Composable
fun PhotoPageNavigation() {
    val navController = rememberNavController()
    val viewModel: PhotoListViewModel = hiltViewModel()
    val navHost =
        NavHost(navController = navController, startDestination = "photo_list_page") {
            navigation(
                startDestination = "list_of_photos",
                route = "photo_list_page"
            ) {
                composable(route = "list_of_photos") {
                    PhotoListComposable(uiStateFlow = viewModel.uiStateFlow) {
                        when (it) {
                            is PhotoListPageUiEvent.NavigateToPhotoDetails -> {
                                navController.navigate("photo_details")
                            }
                        }
                    }
                }
                composable(route = "photo_details") {
                    Text(text = "balsal")
                }
            }
        }
}