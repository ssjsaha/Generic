package com.example.genericapp.feature_photo.presentation.composables

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.genericapp.feature_photo.presentation.ui_events.LoginUiEvent
import com.example.genericapp.feature_photo.presentation.ui_states.UiStateG
import com.google.gson.annotations.Until
import kotlinx.coroutines.flow.StateFlow

@Composable
fun LoginScreen(uiStateFlow: StateFlow<UiStateG>, onEvent: (LoginUiEvent) -> Unit) {
    val uiState = uiStateFlow.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    var emailText by remember {
        mutableStateOf("")
    }
    var passwordText by remember {
        mutableStateOf("")
    }
    if (uiState.value.localError.isNotEmpty()) {
        LaunchedEffect(key1 = uiState.value.localError) {
            snackbarHostState.showSnackbar(uiState.value.localError)
            onEvent(LoginUiEvent.ClearError)
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (uiState.value.loading) {
                CircularProgressIndicator()
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(20.dp, 0.dp, 20.dp, 0.dp),
                        value = emailText, onValueChange = {
                            emailText = it
                        },
                        label = {
                            Text(text = "Email")
                        }
                    )
                    Spacer(
                        modifier = Modifier
                            .height(30.dp)
                            .wrapContentWidth()
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(20.dp, 0.dp, 20.dp, 0.dp),
                        value = passwordText, onValueChange = {
                            passwordText = it
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        interactionSource = remember { MutableInteractionSource() },

                        placeholder = {
                            Text(text = "Password")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password // HERE
                        )
                    )
                    Spacer(
                        modifier = Modifier
                            .height(30.dp)
                            .wrapContentWidth()
                    )
                    OutlinedButton(
                        onClick = {
                            onEvent.invoke(LoginUiEvent.DoLogin(emailText, passwordText))
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 0.dp, 20.dp, 0.dp)
                            .height(50.dp)
                    ) {
                        Text(text = "Login")
                    }
                }
            }
        }

    }
}