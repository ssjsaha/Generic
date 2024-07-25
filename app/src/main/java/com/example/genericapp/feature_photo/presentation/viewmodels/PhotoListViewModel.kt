package com.example.genericapp.feature_photo.presentation.viewmodels

import android.graphics.Point
import android.util.DisplayMetrics
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.genericapp.BuildConfig
import com.example.genericapp.feature_photo.domain.models.PhotoObject
import com.example.genericapp.feature_photo.domain.repositories.PhotoListRepository
import com.example.genericapp.feature_photo.presentation.ui_events.PhotoListPageUiEvent
import com.example.genericapp.feature_photo.presentation.ui_states.PhotoDetailsUiState
import com.example.genericapp.feature_photo.presentation.ui_states.PhotoListUiState
import com.example.genericapp.feature_photo.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val repo: PhotoListRepository,
    private val displayMetrics: DisplayMetrics
) : ViewModel() {

    //ui state for photo list
    private val _uiStateFlow: MutableStateFlow<PhotoListUiState> = MutableStateFlow(
        PhotoListUiState(loading = true)
    )
    val uiStateFlow = _uiStateFlow.asStateFlow()

    // ui state for photo details
    private val _photoDetailsUiState: MutableStateFlow<PhotoDetailsUiState> = MutableStateFlow(
        PhotoDetailsUiState()
    )
    val photoDetailsUiState = _photoDetailsUiState.asStateFlow()

    init {
        getPhotoList()
    }

    fun onEvent(event: PhotoListPageUiEvent) {
        when (event) {
            is PhotoListPageUiEvent.NavigateToPhotoDetails -> {
                _photoDetailsUiState.value = _photoDetailsUiState.value.copy(
                    photo = event.photoObject,
                    isLandscape = isLandscape(event.photoObject.width, event.photoObject.height),
                    photoUrl = constructPhotoUrl(event.photoObject)
                )
            }
            is PhotoListPageUiEvent.RefreshItems -> {
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    isRefreshing = true,
                    loading = true
                )
                getPhotoList()
            }
        }
    }

    fun constructPhotoUrl(photoObject: PhotoObject): String {
        val calculatedWidthAndHeight =
            getPhotoHeightAccordingToDevice(photoObject.width, photoObject.height)
        return "${BuildConfig.BASE_URL}id/${photoObject.id}/${calculatedWidthAndHeight.x}/${calculatedWidthAndHeight.y}"
    }


    @VisibleForTesting
    fun isLandscape(width: Long, height: Long) = width > height

    private fun getPhotoHeightAccordingToDevice(width: Long, height: Long): Point {
        val deviceWidth = displayMetrics.widthPixels
        val calculatedHeight = (height.toFloat() / width.toFloat()) * deviceWidth.toFloat()
        return Point(deviceWidth, calculatedHeight.toInt())
    }

    fun getPhotoList() {
        viewModelScope.launch {
            delay(2000)
            repo.getPhotoList()
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _uiStateFlow.value = _uiStateFlow.value.copy(
                                loading = true
                            )
                        }

                        is Resource.Success -> {
                            _uiStateFlow.value = _uiStateFlow.value.copy(
                                loading = false,
                                isRefreshing = false,
                                photoList = it.data ?: listOf()
                            )

                        }

                        is Resource.Error -> {
                            _uiStateFlow.value = uiStateFlow.value.copy(
                                loading = false,
                                isRefreshing = false,
                                photoList = listOf(),
                                error = it.message
                            )
                        }
                    }
                }
        }
    }
}