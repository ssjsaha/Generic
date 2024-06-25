package com.example.genericapp.feature_photo_list.presentation

sealed class PhotoListPageUiEvent {
    object navigateToPhotoDetails : PhotoListPageUiEvent()
}