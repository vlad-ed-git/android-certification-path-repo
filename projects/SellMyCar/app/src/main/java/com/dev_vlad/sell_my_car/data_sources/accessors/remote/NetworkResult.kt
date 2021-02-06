package com.dev_vlad.sell_my_car.data_sources.accessors.remote


sealed class NetworkResult<out T>{

    data class Success<out T>(val value: T): NetworkResult<T>()

    data class GenericError(
            val code: Int? = null,
            val errorMessage: String? = null
    ): NetworkResult<Nothing>()

    object NetworkError: NetworkResult<Nothing>()

}
