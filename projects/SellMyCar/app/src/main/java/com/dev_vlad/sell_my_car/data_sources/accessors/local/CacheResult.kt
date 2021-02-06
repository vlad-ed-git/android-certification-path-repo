package com.dev_vlad.sell_my_car.data_sources.accessors.local


sealed class CacheResult<out T>{
    data class Success<out T>(val value : T): CacheResult<T>()

    data class GenericError(
        val errorMessage: String? = null
    ): CacheResult<Nothing>()


}
