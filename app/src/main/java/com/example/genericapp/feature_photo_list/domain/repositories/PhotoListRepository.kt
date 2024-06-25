package com.example.genericapp.feature_photo_list.domain.repositories

import com.example.genericapp.feature_photo_list.domain.models.PhotoObject
import com.example.genericapp.feature_photo_list.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PhotoListRepository {
    suspend fun getPhotoList(): Flow<Resource<List<PhotoObject>>>
}