package com.example.genericapp.feature_photo.domain.repositories

import com.example.genericapp.feature_photo.domain.models.PhotoObject
import com.example.genericapp.feature_photo.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PhotoListRepository {
    suspend fun getPhotoList(): Flow<Resource<List<PhotoObject>>>
}