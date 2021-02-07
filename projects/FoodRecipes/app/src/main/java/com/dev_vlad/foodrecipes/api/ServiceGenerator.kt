package com.dev_vlad.foodrecipes.api

import com.dev_vlad.foodrecipes.util.Constants
import com.dev_vlad.foodrecipes.util.network_resources.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private val retrofitBuilder =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())

    private val retrofit  = retrofitBuilder.build()

    //a retrofit instance is then used to instantiate a recipe api
    private val recipeApi : RecipeApi = retrofit.create(RecipeApi::class.java)

    fun getRecipeApi() = recipeApi
}