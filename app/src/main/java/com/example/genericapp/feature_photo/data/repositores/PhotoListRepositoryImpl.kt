package com.example.genericapp.feature_photo.data.repositores

import com.example.genericapp.feature_photo.data.Api
import com.example.genericapp.feature_photo.domain.models.PhotoObject
import com.example.genericapp.feature_photo.domain.repositories.PhotoListRepository
import com.example.genericapp.feature_photo.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PhotoListRepositoryImpl @Inject constructor(private val api: Api) : PhotoListRepository {
    override suspend fun getPhotoList(): Flow<Resource<List<PhotoObject>>> = flow {
        emit(Resource.Loading())
        try {
            val res = api.getPhotoList()
            if (res.isSuccessful) {
                emit(Resource.Success(res.body()))
            } else {
                emit(Resource.Error(res.errorBody()?.string() ?: "Something went wrong"))
            }
        } catch (e: Exception) {
            ///todo///
            /*
            handle network exception, no network issue separately
            * */
            emit(Resource.Error(e.message ?: "Something went wrong"))
        }

    }

}