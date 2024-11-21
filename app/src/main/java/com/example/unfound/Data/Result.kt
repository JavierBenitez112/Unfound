package com.example.unfound.Data

sealed class DataError {
    object NO_INTERNET : DataError()
    object GENERIC_ERROR : DataError()
}

sealed class Result<out T, out E> {
    data class Success<out T>(val data: T) : Result<T, Nothing>()
    data class Error<out E>(val error: E) : Result<Nothing, E>()
}