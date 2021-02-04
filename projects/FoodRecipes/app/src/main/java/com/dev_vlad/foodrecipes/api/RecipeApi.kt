package com.dev_vlad.foodrecipes.api

import com.dev_vlad.foodrecipes.api.responses.RecipeResponse
import com.dev_vlad.foodrecipes.api.responses.RecipeSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*
** specifies how the request is to be made
*  the method and parameters to pass
 */
interface RecipeApi {
    // SEARCH
    @GET("api/search")
    fun searchRecipe(
            @Query("key") key: String?,
            @Query("q") query: String?,
            @Query("page") page: String?
    ): Call<RecipeSearchResponse?>?

    // GET RECIPE REQUEST
    @GET("api/get")
    fun getRecipe(
            @Query("key") key: String?,
            @Query("rId") recipe_id: String?
    ): Call<RecipeResponse?>?
}