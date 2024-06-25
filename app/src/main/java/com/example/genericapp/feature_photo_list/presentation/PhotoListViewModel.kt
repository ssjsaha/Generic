package com.example.genericapp.feature_photo_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.genericapp.feature_photo_list.domain.repositories.PhotoListRepository
import com.example.genericapp.feature_photo_list.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(private val repo: PhotoListRepository) : ViewModel() {


    private fun getPhotoList() {
        viewModelScope.launch {
            repo.getPhotoList().collect {
                when (it) {
                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {

                    }

                    is Resource.Error -> {

                    }
                }
            }
        }
    }

}