package com.example.genericapp.feature_photo.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.genericapp.feature_photo.presentation.ui_events.LoginUiEvent
import com.example.genericapp.feature_photo.presentation.ui_states.UiStateG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _uiStateG: MutableStateFlow<UiStateG> = MutableStateFlow(UiStateG())
    val uiStateG = _uiStateG.asStateFlow()


    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.DoLogin -> {
                val localError = checkInput(event.email, event.password)
                if (localError.isEmpty()) {
                    _uiStateG.value = _uiStateG.value.copy(
                        loading = true
                    )
                    doLogin(event.email, event.password)
                } else {
                    _uiStateG.value = _uiStateG.value.copy(
                        localError = checkInput(event.email, event.password)
                    )
                }

            }

            is LoginUiEvent.ClearError -> {
                _uiStateG.value = _uiStateG.value.copy(
                    localError = ""
                )
            }
        }
    }

    fun doLogin(email: String, password: String) {
        viewModelScope.launch {
            delay(2000)
            if (email == "a" && password == "a") {

            } else {
                _uiStateG.value = _uiStateG.value.copy(
                    loading = false,
                    localError = "Wrong username or password"
                )
            }
        }

    }

    fun checkInput(email: String, password: String): String {
        if (email.isEmpty()) return "Enter email"
        else if (password.isEmpty()) return "Enter password"
        else return ""
    }

}