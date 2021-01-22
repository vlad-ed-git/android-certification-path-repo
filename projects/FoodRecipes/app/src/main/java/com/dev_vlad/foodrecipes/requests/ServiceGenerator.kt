package com.dev_vlad.foodrecipes.requests

import com.dev_vlad.foodrecipes.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private val retrofitBuilder =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    private val retrofit  = retrofitBuilder.build()

    //a retrofit instance is then used to instantiate a recipe api
    private val recipeApi : RecipeApi = retrofit.create(RecipeApi::class.java)

    fun getRecipeApi() = recipeApi
}