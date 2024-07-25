package com.example.genericapp.feature_photo.presentation.composables

import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import com.example.genericapp.BuildConfig
import com.example.genericapp.createImageFile
import java.io.File

@Composable
fun SelectImageUpload() {
    val context = LocalContext.current

    var showDialog by remember {
        mutableStateOf(false)
    }
    var file by remember {
        mutableStateOf(context.createImageFile())
    }

    var uri by remember {
        mutableStateOf(
            FileProvider.getUriForFile(
                context, BuildConfig.APPLICATION_ID + ".provider", file
            )
        )
    }

    var capturedUri by remember {
        mutableStateOf(Uri.EMPTY)
    }


    var galleryPermissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {

            } else {

            }

        }
    val cameraLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) {
            if (it) {
                capturedUri = uri
            }
        }
    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                file = context.createImageFile()
                uri = FileProvider.getUriForFile(
                    context, BuildConfig.APPLICATION_ID + ".provider", file)
                cameraLauncher.launch(uri)
            } else {

            }

        }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(200.dp, 200.dp),
            painter = rememberAsyncImagePainter(capturedUri),
            contentDescription = ""
        )
        Button(
            onClick = {
                showDialog = true
            }, modifier = Modifier
                .fillMaxWidth()
                .size(56.dp)
                .padding(20.dp, 0.dp, 20.dp, 0.dp)
        ) {
            Text(text = "Select Image")
        }
        if (showDialog) {
            CustomAlertDialog(onDismiss = { showDialog = false }, onCameraSelect = {
                showDialog = false
                if (ContextCompat.checkSelfPermission(
                        context, android.Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(context, "Permission camera granted", Toast.LENGTH_SHORT).show()
                    file = context.createImageFile()
                    uri = FileProvider.getUriForFile(
                        context, BuildConfig.APPLICATION_ID + ".provider", file)
                    cameraLauncher.launch(uri)
                } else {
                    cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                }
            }, onGallerySelect = {
                showDialog = false
                showDialog = false
                if (ContextCompat.checkSelfPermission(
                        context, android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(context, "Permission camera granted", Toast.LENGTH_SHORT).show()
                } else {
                    cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                }

            })
        }
    }
}

@Composable
fun CustomAlertDialog(
    onDismiss: () -> Unit, onCameraSelect: () -> Unit, onGallerySelect: () -> Unit
) {
    AlertDialog(title = {
        Text(text = "Select Image")
    }, text = {
        Text(text = "Please select one of the options below")
    }, onDismissRequest = {
        onDismiss()
    }, confirmButton = {
        TextButton(onClick = { onCameraSelect() }) {
            Text(text = "Camera")
        }
    }, dismissButton = {
        TextButton(onClick = { onGallerySelect() }) {
            Text(text = "Gallery")
        }
    })
}