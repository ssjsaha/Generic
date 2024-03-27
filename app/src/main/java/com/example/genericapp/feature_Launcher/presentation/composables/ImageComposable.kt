package com.example.genericapp.feature_Launcher.presentation.composables

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.genericapp.R
import com.example.genericapp.feature_Launcher.presentation.LauncherUIEvent


@Composable
fun ImageComposable() {
    val activity = (LocalContext.current as? Activity)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .padding(30.dp)
                .size(500.dp)
                .clip(RoundedCornerShape(10)),
            painter = painterResource(id = R.drawable.monuku),
            contentDescription = "monuku_bunuku"
        )
        Spacer(modifier = Modifier.size(30.dp))
        Text(
            modifier = Modifier.padding(20.dp),
            text = stringResource(id = R.string.see_your_face),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(30.dp))

        OutlinedButton(
            border = BorderStroke(1.dp, Color.Blue), // Border stroke with 1dp width
            shape = RoundedCornerShape(16.dp), // 16dp rounded corners
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(0.8f), // Add padding as needed
            onClick = {
                activity?.finish()
            }) {

            Text(text = stringResource(id = R.string.end))
        }

    }
}