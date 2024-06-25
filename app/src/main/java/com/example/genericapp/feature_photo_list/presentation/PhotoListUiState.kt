package com.example.genericapp.feature_photo_list.presentation

import com.example.genericapp.feature_photo_list.domain.models.PhotoObject

data class PhotoListUiState(
    var photoList: List<PhotoObject> = listOf(),
    var loading: Boolean = false,
    var error: String = ""
)