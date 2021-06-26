package com.bharath.rickyandmortyseries.repository

import retrofit2.Response

data class NetworkResponseHelper<T>(
    val status: Status,
    val data: Response<T>?,
    val exception: Exception?
) {

    companion object{
        fun <T> success(data: Response<T>): NetworkResponseHelper<T>{
            return NetworkResponseHelper(
                status = Status.success,
                data = data,
                exception = null
            )
        }

        fun <T> failure(exception: Exception): NetworkResponseHelper<T>{
            return NetworkResponseHelper(
                status = Status.failure,
                data = null,
                exception = exception
            )
        }
    }

    sealed class Status{
        object success: Status()
        object failure: Status()
    }


    val failed: Boolean
        get() = this.status == Status.failure

    val isSuccessful: Boolean
        get() = !failed && this.data?.isSuccessful == true

    val body: T
        get() = this.data!!.body()!!
}