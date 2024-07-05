package com.example.genericapp.feature_photo.presentation.ui_states

import com.example.genericapp.feature_photo.domain.models.PhotoObject

data class PhotoDetailsUiState(
    var photo: PhotoObject? = null,
    var isLandscape: Boolean = false,
    var photoUrl: String? = null
)