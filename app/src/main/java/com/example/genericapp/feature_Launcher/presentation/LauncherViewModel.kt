package com.example.genericapp.feature_Launcher.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class LauncherViewModel : ViewModel() {

    private var _launcherUiState: MutableStateFlow<LauncherUiState> = MutableStateFlow(LauncherUiState(navigate = false))
    val launcherUiState = _launcherUiState.asStateFlow()
    fun onEvent(event: LauncherUIEvent) {
        when (event) {
            is LauncherUIEvent.NavigateToMainScreen -> {
                _launcherUiState.value = _launcherUiState.value.copy(
                    navigate = true
                )
            }
        }
    }
}