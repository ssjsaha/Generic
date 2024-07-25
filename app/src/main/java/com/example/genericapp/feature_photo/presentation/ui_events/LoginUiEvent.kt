package com.example.genericapp.feature_photo.presentation.ui_events

sealed class LoginUiEvent {
    data object ClearError : LoginUiEvent()
    class DoLogin(val email: String, val password: String): LoginUiEvent()
}