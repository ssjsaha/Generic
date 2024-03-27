package com.example.genericapp

import MainBalloonComposables
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.genericapp.feature_Launcher.presentation.LauncherViewModel
import com.example.genericapp.feature_Launcher.presentation.MainViewModel
import com.example.genericapp.feature_Launcher.presentation.composables.CandleComposable
import com.example.genericapp.feature_Launcher.presentation.composables.ImageComposable
import com.example.genericapp.feature_Launcher.presentation.composables.StarterComposables

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val navHost =
        NavHost(navController = navController, startDestination = "candle") {
            composable(route = "launcher") {
                val viewModel = it.sharedViewModel<LauncherViewModel>(navController = navController)
                StarterComposables(
                    onEvent = viewModel::onEvent,
                    state = viewModel.launcherUiState,
                    controller = navController
                )
            }
            composable(route = "main") {
                val viewModel = it.sharedViewModel<MainViewModel>(navController = navController)
                MainBalloonComposables(state = viewModel.mainUiState) {
                    navController.navigate("image")
                }
            }
            composable(route = "candle") {
                CandleComposable {
                    navController.navigate("launcher")
                }
            }
            composable(route = "image") {
                ImageComposable()
            }
        }
}