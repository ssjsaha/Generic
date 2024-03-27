package com.example.genericapp.feature_Launcher.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.genericapp.R
import kotlinx.coroutines.delay

@Composable
fun CandleComposable(onNavigate: () -> Unit) {
    val compositionFire by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.fire),
    )
    val compositionSmoke by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.smoke)
    )

    val compositionCake by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.birthday_cake_cut)
    )

    var isFireTouched by remember {
        mutableStateOf(false)
    }

    var isCakeShow by remember {

        mutableStateOf(false)
    }
    if (!isCakeShow) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(modifier = Modifier) {

                if (isFireTouched) {
                    Spacer(modifier = Modifier.width(70.dp))
                    LaunchedEffect(Unit) {
                        delay(3000)
                        isCakeShow = true
                    }
                }
                LottieAnimation(
                    modifier = Modifier
                        .scale(1f)
                        .size(200.dp)
                        .clickable(
                            onClick = {
                                isFireTouched = true
                            },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                    iterations = 1000,
                    composition = if (isFireTouched) {
                        compositionSmoke
                    } else {
                        compositionFire
                    }
                )
            }

            Image(
                painter = painterResource(id = R.drawable.candle2),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(400.dp)
            )

        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            LottieAnimation(
                modifier = Modifier
                    .scale(1f)
                    .wrapContentSize(),
                iterations = 1000,
                composition = compositionCake
            )
            LaunchedEffect(Unit) {
                delay(4000)
                onNavigate()
            }

        }
    }

}