package com.example.genericapp.domain

data class Post(
    val id: Long,
    val userId: String,
    val title: String,
    val body: String
)
