package com.example.palomadeapps.data

sealed class ResponseState<out T>() {
    data class Loading<T>(val isLoading: Boolean) : ResponseState<T>()
    data class Success<T>(val data: T) : ResponseState<T>()
    data class Error<T>(val message: String) : ResponseState<T>()
}