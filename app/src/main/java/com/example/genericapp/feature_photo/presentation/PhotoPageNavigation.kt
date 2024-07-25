package com.example.genericapp.feature_photo.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.genericapp.feature_photo.presentation.composables.LoginScreen
import com.example.genericapp.feature_photo.presentation.composables.PhotoDetailsComposable
import com.example.genericapp.feature_photo.presentation.composables.PhotoListComposable
import com.example.genericapp.feature_photo.presentation.composables.SelectImageUpload
import com.example.genericapp.feature_photo.presentation.composables.SplashComposable
import com.example.genericapp.feature_photo.presentation.composables.TopBarComposable
import com.example.genericapp.feature_photo.presentation.viewmodels.LoginViewModel
import com.example.genericapp.feature_photo.presentation.viewmodels.PhotoListViewModel
import kotlinx.coroutines.delay


@Composable
fun PhotoPageNavigation() {
    /*    val navController = rememberNavController()
        val viewModel: PhotoListViewModel = hiltViewModel()
        val navHost =
            NavHost(navController = navController, startDestination = "photo_list_page") {
                navigation(
                    startDestination = "splash",
                    route = "photo_list_page"
                ) {
                    composable(route = "splash") {
                        SplashComposable()
                        LaunchedEffect(key1 = true) {
                            delay(2000)
                            navController.navigate("list_of_photos"){
                                popUpTo("splash"){
                                    inclusive = true
                                }
                            }
                        }
                    }
                    composable(route = "list_of_photos") {
                        PhotoListComposable(
                            uiStateFlow = viewModel.uiStateFlow,
                            onEvent = viewModel::onEvent,
                        ) {
                            navController.navigate("photo_details")
                        }
                    }
                    composable(route = "photo_details") {
                        PhotoDetailsComposable(uiState = viewModel.photoDetailsUiState) {
                            navController.popBackStack()
                        }
                    }
                }
            }*/
    val navController = rememberNavController()

    val navHost = NavHost(navController = navController, startDestination = "image_upload") {
        navigation(startDestination = "image", route = "image_upload")
        {

            composable(route = "image") {
                val viewModel: LoginViewModel = hiltViewModel()
                LoginScreen(viewModel.uiStateG, viewModel::onEvent)
            }
        }
    }
}