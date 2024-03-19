package com.example.genericapp.feature_Launcher.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.genericapp.feature_Launcher.presentation.composables.MainUiEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


    private val _mainUiState: MutableStateFlow<BalloonUiState> =
        MutableStateFlow(BalloonUiState(false))

    val mainUiState = _mainUiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(100)
            _mainUiState.value = _mainUiState.value.copy(
                start = true,
                ballonNumber = 1
            )
        }
    }

    fun onEvent(event: MainUiEvent) {
        when (event) {
            MainUiEvent.Restart -> {
                viewModelScope.launch {
                    _mainUiState.value = _mainUiState.value.copy(
                        start = false
                    )
                    delay(10000)
                    _mainUiState.value = _mainUiState.value.copy(
                        start = true
                    )
                }

            }
        }
    }

}