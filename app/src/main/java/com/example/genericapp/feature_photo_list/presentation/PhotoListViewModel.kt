package com.example.genericapp.feature_photo_list.presentation

import android.provider.ContactsContract.Contacts.Photo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.genericapp.feature_photo_list.domain.models.PhotoObject
import com.example.genericapp.feature_photo_list.domain.repositories.PhotoListRepository
import com.example.genericapp.feature_photo_list.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(private val repo: PhotoListRepository) : ViewModel() {

    private val _uiStateFlow: MutableStateFlow<PhotoListUiState> = MutableStateFlow(
        PhotoListUiState()
    )
    val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        getPhotoList()
    }

    fun test(): Int {
        return 2 + 3
    }

    fun getPhotoList() {
        viewModelScope.launch {
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
                                photoList = it.data ?: listOf()
                            )

                        }
                        is Resource.Error -> {
                            _uiStateFlow.value = uiStateFlow.value.copy(
                                loading = false,
                                photoList = listOf(),
                                error = it.message
                            )
                        }
                    }
                }
        }
    }
}