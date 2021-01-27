package com.dev_vlad.foodrecipes.repositories

import androidx.lifecycle.LiveData
import com.dev_vlad.foodrecipes.api.RecipeApiClient
import com.dev_vlad.foodrecipes.models.Recipe

object RecipeRepo {

    private var currentQuery:String = ""
    private var currentPage :Int = 1

    fun getRecipes() : LiveData<ArrayList<Recipe>> {
        return RecipeApiClient.getRecipes()
    }

    fun getARecipe() : LiveData<Recipe> {
        return RecipeApiClient.getRecipe()
    }

    fun hasRecipesRequestTimeout() : LiveData<Boolean> {
        return RecipeApiClient.hasRecipesRequestTimeout()
    }

    fun hasRecipeRequestTimeout() : LiveData<Boolean> {
        return RecipeApiClient.hasRecipeRequestTimeout()
    }

    fun searchRecipesApi(query:String, page:Int = 1){
        currentQuery = query
        currentPage = page
        RecipeApiClient.searchRecipesApi(query, page)
    }

    fun searchRecipeByIdApi(recipeId:String){
        RecipeApiClient.searchRecipeByIdApi(recipeId = recipeId)
    }


    fun fetchNextResults(){
        currentPage += 1
        RecipeApiClient.searchRecipesApi(currentQuery, currentPage)

    }

    fun cancelSearchRecipesRequest(){
        RecipeApiClient.cancelSearchRecipesRequest()
    }

    fun cancelGetRecipeRequest() {
        RecipeApiClient.cancelGetRecipeRequest()
    }
}