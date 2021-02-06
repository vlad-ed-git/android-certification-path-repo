package com.dev_vlad.foodrecipes.util.network_resources

import retrofit2.Response
import java.io.IOException


/**
 * Generic class for handling responses from Retrofit
 * @param <T>
</T> */
sealed class GenericApiResponse<T> {
    companion object {

        fun <T> create(error: Throwable): GenericApiResponse<T> {
            val errMsg =
                if (error.message.isNullOrEmpty()) "Unknown error\nCheck network connection" else error.message
            return GenericApiErrorResponse<T>(errorMessage = errMsg)
        }

        fun <T> create(response: Response<T>): GenericApiResponse<T> {
            return when {

                response.isSuccessful -> {
                    val body: T? = response.body()

                    if (body == null || response.code() == 204) {
                        // 204 is empty response
                        GenericApiEmptyResponse<T>()
                    } else {
                        GenericApiSuccessResponse<T>(body)
                    }

                }
                else -> {
                    //response is not successful
                    var errorMsg = ""
                    errorMsg = try {
                        response.errorBody().toString()
                    } catch (e: IOException) {
                        e.printStackTrace()
                        response.message()
                    }
                    GenericApiErrorResponse<T>(errorMsg)
                }
            }
        }

        /**
         * Generic success response from api
         * @param <T>
        </T> */
        data class GenericApiSuccessResponse<T> (val body: T) : GenericApiResponse<T>()

        /**
         * Generic Error response from API
         * @param <T>
        </T> */
        data class GenericApiErrorResponse<T> (val errorMessage: String?) : GenericApiResponse<T>()

        /**
         *  sometimes the response is successful but body is null == 204 NO Content
         * separate class for HTTP 204 responses so that we can make GenericApiSuccessResponse's body non-null.
         */
        class GenericApiEmptyResponse<T> : GenericApiResponse<T>()

    }
}



