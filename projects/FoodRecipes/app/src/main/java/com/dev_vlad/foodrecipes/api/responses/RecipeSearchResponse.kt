package com.dev_vlad.foodrecipes.api.responses

import com.dev_vlad.foodrecipes.models.Recipe
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RecipeSearchResponse {

    @SerializedName("count")
    @Expose()
    private var count : Int = 0;

    @SerializedName("recipes")
    @Expose()
    private var recipes : List<Recipe> = arrayListOf()

    override fun toString(): String {
        return "${RecipeSearchResponse::class.java.simpleName} : results count = $count"
    }
}