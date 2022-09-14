package com.ozan.listapp.data.network.response

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val error: Exception) : Result<Nothing>()

    suspend fun use(onSuccess: suspend (T) -> Unit, onFailed: suspend (Exception) -> Unit) {
        when (this) {
            is Success -> onSuccess(data)
            is Error -> onFailed(error)
        }
    }
}

