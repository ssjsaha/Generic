import android.graphics.fonts.FontStyle
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.genericapp.R
import com.example.genericapp.feature_Launcher.presentation.BalloonUiState
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.roundToInt

@Composable
fun MainBalloonComposables(state: StateFlow<BalloonUiState>, onClick: () -> Unit) {
    val uiState = state.collectAsState()
    val move = uiState.value.start

    val pxToMove = with(LocalDensity.current) {
        LocalConfiguration.current.screenHeightDp.dp.toPx().roundToInt()
    }

    val gradientColors = listOf(Cyan, Red, Magenta)

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

    val offset2 by animateIntOffsetAsState(
        targetValue = if (move) {
            IntOffset(0, -pxToMove)
        } else {
            IntOffset(0, 600)
        },
        label = "offset",
        animationSpec = infiniteRepeatable(
            animation = tween(10000, delayMillis = 900, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(30.dp),
            text = stringResource(id = R.string.happy_birthday_monuku),
            fontSize = 24.sp,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = gradientColors
                )
            ),
            textAlign = TextAlign.Center

        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (!uiState.value.showButton) {
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
                                offset2
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
                } else if (uiState.value.showButton) {
                    OutlinedButton(
                        border = BorderStroke(1.dp, Color.Blue), // Border stroke with 1dp width
                        shape = RoundedCornerShape(16.dp), // 16dp rounded corners

                        modifier = Modifier
                            .padding(50.dp)
                            .fillMaxWidth(0.8f)
                            .weight(1f),
                        onClick = {
                            onClick()
                        }) {
                        Text(text = stringResource(id = R.string.finish))
                    }
                }
            }


        }
    }

}
