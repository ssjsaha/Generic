package com.example.genericapp.data

import com.example.genericapp.feature_photo.domain.models.PhotoObject
import com.example.genericapp.feature_photo.domain.repositories.PhotoListRepository
import com.example.genericapp.feature_photo.utils.Resource
import com.example.genericapp.utils.FakeVerdict
import com.example.genericapp.utils.dummyError
import com.example.genericapp.utils.dummyObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePhotoListRepoImpl(private val verdict: FakeVerdict) : PhotoListRepository {
    override suspend fun getPhotoList(): Flow<Resource<List<PhotoObject>>> = flow {
        emit(Resource.Loading())
        when (verdict) {
            FakeVerdict.ERROR -> {
                emit(Resource.Error(dummyError))
            }

            FakeVerdict.SUCCESS -> {
                emit(Resource.Success(listOf(dummyObject)))
            }
        }
    }
}