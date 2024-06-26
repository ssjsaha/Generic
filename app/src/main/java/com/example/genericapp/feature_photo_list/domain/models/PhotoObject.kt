package com.example.genericapp.feature_photo_list.domain.models

import com.squareup.moshi.Json


data class PhotoObject(
    val format: String,
    val width: Long,
    val height: Long,
    val filename: String,
    val id: Long,
    val author: String,

    @Json(name = "author_url")
    val authorUrl: String,

    @Json(name = "post_url")
    val postUrl: String
)