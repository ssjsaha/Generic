import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.genericapp.R
import com.example.genericapp.feature_Launcher.presentation.BalloonUiState
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.roundToInt

@Composable
fun MainBalloonComposables(state: StateFlow<BalloonUiState>) {
    val uiState = state.collectAsState()
    val move = uiState.value.start
    val pxToMove = with(LocalDensity.current) {
        LocalConfiguration.current.screenHeightDp.dp.toPx().roundToInt()
    }
    val offset by animateIntOffsetAsState(
        targetValue = if (move) {
            IntOffset(0, -pxToMove)
        } else {
            IntOffset(0, 600)
        },
        label = "offset",
        animationSpec = infiniteRepeatable(
            animation = tween(10000, delayMillis = 100, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.baloon2),
                contentDescription = "",
                modifier = Modifier
                    .weight(0.3f)
                    .size(200.dp)
                    .offset {
                        offset
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.baloon1),
                contentDescription = "",
                modifier = Modifier
                    .weight(0.3f)
                    .size(200.dp)
                    .offset {
                        offset
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.baloon1),
                contentDescription = "",
                modifier = Modifier
                    .weight(0.3f)
                    .size(200.dp)
                    .offset {
                        offset
                    }
            )
        }

    }

}
