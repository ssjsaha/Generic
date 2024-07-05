package com.example.genericapp.feature_photo.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.genericapp.feature_photo.presentation.composables.PhotoDetailsComposable
import com.example.genericapp.feature_photo.presentation.composables.PhotoListComposable
import com.example.genericapp.feature_photo.presentation.viewmodels.PhotoListViewModel


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
                    PhotoListComposable(
                        uiStateFlow = viewModel.uiStateFlow,
                        onEvent = viewModel::onEvent,
                    ) {
                        navController.navigate("photo_details")
                    }
                }
                composable(route = "photo_details") {
                    PhotoDetailsComposable(uiState = viewModel.photoDetailsUiState)
                }
            }
        }
}