package com.example.genericapp.network

import com.example.genericapp.domain.Post
import com.example.genericapp.network.model.GenericResponse
import retrofit2.http.GET

interface Api {
    @GET("posts")
    suspend fun getPosts(): GenericResponse<List<Post>>
}