package com.example.genericapp.feature_photo_list.data.repositores

import com.example.genericapp.feature_photo_list.domain.models.PhotoObject
import com.example.genericapp.feature_photo_list.domain.repositories.PhotoListRepository


class PhotoListRepositoryImpl:  PhotoListRepository{
    override suspend fun getPhotoList(): List<PhotoObject> {

    }

}