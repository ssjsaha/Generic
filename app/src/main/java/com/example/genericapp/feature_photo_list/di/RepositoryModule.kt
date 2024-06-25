package com.example.genericapp.feature_photo_list.di

import com.example.genericapp.feature_photo_list.data.repositores.PhotoListRepositoryImpl
import com.example.genericapp.feature_photo_list.domain.repositories.PhotoListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPhotoRepository(photoRepoImpl: PhotoListRepositoryImpl): PhotoListRepository
}