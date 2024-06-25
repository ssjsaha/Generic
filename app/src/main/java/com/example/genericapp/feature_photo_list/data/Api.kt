package com.example.genericapp.feature_photo_list.data

import com.example.genericapp.feature_photo_list.domain.models.PhotoObject
import retrofit2.Response

interface Api {

    suspend fun getPhotoList(): Response<List<PhotoObject>>
}