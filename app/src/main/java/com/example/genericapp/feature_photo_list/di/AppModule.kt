package com.example.genericapp.feature_photo_list.di

import com.example.genericapp.feature_photo_list.data.Api
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Api {
        /*val moshi = Moshi.Builder().add(MoshiConverterFactory.create()).build()
        return Retrofit.Builder()
            .baseUrl("https://picsum.photos/")
            .addConverterFactory(moshi)
            .build().create()*/
    }
}