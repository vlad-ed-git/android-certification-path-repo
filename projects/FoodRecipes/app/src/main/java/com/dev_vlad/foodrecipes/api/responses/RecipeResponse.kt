package com.dev_vlad.foodrecipes.api.responses

import com.dev_vlad.foodrecipes.models.Recipe
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/** response retrieved when a get recipe api call returns */
class RecipeResponse {


    //tell retrofit what key points to the object being serialized
    //then expose it the G-son converter
    @SerializedName("recipe")
    @Expose
    private var recipe: Recipe? = null

    fun getRecipe(): Recipe? {
        return recipe
    }

    override fun toString(): String {
        return "${RecipeResponse::class.java.simpleName} : $recipe"
    }
}