package com.example.genericapp.feature_photo_list.data

import com.example.genericapp.feature_photo_list.domain.models.PhotoObject
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("list")
    suspend fun getPhotoList(): Response<List<PhotoObject>>
}