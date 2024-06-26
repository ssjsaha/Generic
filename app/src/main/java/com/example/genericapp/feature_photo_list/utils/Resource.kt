package com.example.genericapp.feature_photo_list.utils


sealed class Resource<T>(val data: T? = null, val message: String = "") {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(message = message)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>(null)
}
