package com.ozan.listapp.util.extension

import com.ozan.listapp.data.network.response.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend inline fun <T : Any> Response<T>.toResult(): Result<T> =
    withContext(Dispatchers.IO) {
        when {
            isSuccessful && body() != null -> Result.Success(body() as T)

            else ->
                Result.Error(
                    Exception(errorBody().toString())
                )
        }
    }