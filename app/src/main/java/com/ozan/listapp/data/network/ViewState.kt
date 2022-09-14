package com.ozan.listapp.data.network

class ViewState<T>(
    val status: Status,
    val data: T? = null,
    val error: Throwable? = null
) {

    fun isSuccess() = status == Status.SUCCESS

    fun isLoading() = status == Status.LOADING

    fun getErrorMessage() = error?.message ?: "unKnow"

    fun shouldShowErrorMessage() = error != null && status == Status.ERROR
}

enum class Status {
    LOADING,
    SUCCESS,
    ERROR,
    NONE
}