package com.example.genericapp

import MainBalloonComposables
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.genericapp.feature_Launcher.presentation.LauncherViewModel
import com.example.genericapp.feature_Launcher.presentation.MainViewModel
import com.example.genericapp.feature_Launcher.presentation.composables.StarterComposables

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val navHost =
        NavHost(navController = navController, startDestination = "Launcher") {
            composable(route = "Launcher") {
                val viewModel = it.sharedViewModel<LauncherViewModel>(navController = navController)
                StarterComposables(
                    onEvent = viewModel::onEvent,
                    state = viewModel.launcherUiState,
                    controller = navController
                )
            }
            composable(route = "Main") {
                val viewModel = it.sharedViewModel<MainViewModel>(navController = navController)
                MainBalloonComposables(state = viewModel.mainUiState)
            }
        }
}