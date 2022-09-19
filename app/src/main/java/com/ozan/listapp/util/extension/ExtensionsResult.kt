package com.ozan.listapp.util.extension

import com.ozan.listapp.data.network.response.Result

inline fun <R : Any> resultOf(block: () -> R): Result<R> {
    return try {
        Result.Success(block())
    } catch (e: Exception) {
        Result.Error(e)
    }
}