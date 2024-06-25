package com.example.genericapp.feature_photo_list.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

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

                }
            }
        }
}