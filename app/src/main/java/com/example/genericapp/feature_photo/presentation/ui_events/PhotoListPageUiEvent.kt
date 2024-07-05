package com.example.genericapp.feature_photo.presentation.ui_events

import com.example.genericapp.feature_photo.domain.models.PhotoObject

sealed class PhotoListPageUiEvent {
    data class NavigateToPhotoDetails(val photoObject: PhotoObject) : PhotoListPageUiEvent()
    data object RefreshItems: PhotoListPageUiEvent()
}