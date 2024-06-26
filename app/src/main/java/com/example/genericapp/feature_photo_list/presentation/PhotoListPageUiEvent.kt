package com.example.genericapp.feature_photo_list.presentation

import com.example.genericapp.feature_photo_list.domain.models.PhotoObject

sealed class PhotoListPageUiEvent {
    data class NavigateToPhotoDetails(val photoObject: PhotoObject) : PhotoListPageUiEvent()
}