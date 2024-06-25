package com.example.genericapp.feature_photo_list.domain.repositories

import com.example.genericapp.feature_photo_list.domain.models.PhotoObject

interface PhotoListRepository {
    suspend fun getPhotoList(): List<PhotoObject>
}