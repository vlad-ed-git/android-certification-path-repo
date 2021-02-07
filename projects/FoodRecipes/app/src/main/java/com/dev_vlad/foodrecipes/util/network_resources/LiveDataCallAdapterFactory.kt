package com.dev_vlad.foodrecipes.util.network_resources

import androidx.lifecycle.LiveData
import retrofit2.CallAdapter
import retrofit2.CallAdapter.Factory
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/*
*** produces the live call adapter classes for the api service generator
 */
class LiveDataCallAdapterFactory : Factory() {


    /**
     * This method performs a number of checks and then returns the Response type for the Retrofit requests.
     * (@bodyType is the ResponseType. It can be RecipeResponse or RecipeSearchResponse)
     *
     * CHECK #1) returnType returns LiveData
     * CHECK #2) Type LiveData<T> is of ApiResponse.class
     * CHECK #3) Make sure ApiResponse is parameterized. AKA: ApiResponse<T> exists.
     *
     *
     */
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        // Check #1
        // Make sure the CallAdapter is returning a type of LiveData
        if (Factory.getRawType(returnType) != LiveData::class.java) {
            return null
        }

        // Check #2
        // check the Type that LiveData is wrapping
        val observableType = Factory.getParameterUpperBound(0, returnType as ParameterizedType)

        // Check if it's of Type GeneralApiResponse
        val rawObservableType = Factory.getRawType(observableType)
        if (rawObservableType != GenericApiResponse::class.java) {
            throw IllegalArgumentException("type must be a resource")
        }

        // Check #3
        // Check if GeneralApiResponse is parameterized. AKA: Does ApiResponse<T> exists? (must wrap around T)
        // FYI: T is either RecipeResponse or T will be a RecipeSearchResponse
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }


        val bodyType = Factory.getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter<Any>(bodyType)
    }
}