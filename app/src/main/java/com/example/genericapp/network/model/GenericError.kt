package com.example.genericapp.network.model

data class GenericError (
    val status: Throwable?,
    val errors: List<String>
)
