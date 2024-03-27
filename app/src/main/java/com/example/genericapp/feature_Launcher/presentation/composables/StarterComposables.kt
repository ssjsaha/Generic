package com.example.genericapp.feature_Launcher.presentation.composables

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.genericapp.R
import com.example.genericapp.feature_Launcher.presentation.LauncherUIEvent
import com.example.genericapp.feature_Launcher.presentation.LauncherUiState
import com.example.genericapp.ui.theme.Purple80
import com.example.genericapp.ui.theme.lightCyan
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun StarterComposables(
    modifier: Modifier = Modifier,
    onEvent: (LauncherUIEvent) -> Unit,
    state: StateFlow<LauncherUiState>,
    controller: NavHostController
) {
    val scope = rememberCoroutineScope()
    val uiState = state.collectAsState()
    var isEnabled by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit) {
        delay(3000)
        isEnabled = true
    }
    if (uiState.value.navigate) {
        LaunchedEffect(key1 = Unit) {
            controller.navigate("main")

        }
    } else {
        val compositionCelebration by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.anim_b),
        )
        val compositionHappyBirthday by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.birthday)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = lightCyan),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieAnimation(
                    modifier = Modifier
                        .fillMaxSize(0.5f)
                        .scale(3f),
                    iterations = 2,
                    composition = compositionHappyBirthday,
                )
                LottieAnimation(
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .scale(2f),
                    iterations = 1000,
                    composition = compositionCelebration,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 0.dp, 0.dp, 30.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedButton(
                    border = BorderStroke(1.dp, Color.Blue), // Border stroke with 1dp width
                    shape = RoundedCornerShape(16.dp), // 16dp rounded corners
                    enabled = isEnabled,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(0.8f), // Add padding as needed
                    onClick = {
                        onEvent.invoke(LauncherUIEvent.NavigateToMainScreen)
                    }) {
                    Text(text = stringResource(id = R.string.see_balloons))
                }
            }

        }
    }
}