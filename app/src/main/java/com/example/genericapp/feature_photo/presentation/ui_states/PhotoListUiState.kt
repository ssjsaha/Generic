package com.example.genericapp.feature_photo.presentation.ui_states

import com.example.genericapp.feature_photo.domain.models.PhotoObject

data class PhotoListUiState(
    var photoList: List<PhotoObject> = listOf(),
    var loading: Boolean = false,
    var error: String = "",
    var isRefreshing: Boolean = false
)