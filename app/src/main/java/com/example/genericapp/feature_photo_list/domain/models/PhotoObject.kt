package com.example.genericapp.feature_photo_list.domain.models

data class PhotoObject(
    val format: String,
    val width: Long,
    val height: Long,
    val filename: String,
    val id: Long,
    val author: String,
    val authorUrl: String,
    val postUrl: String
)