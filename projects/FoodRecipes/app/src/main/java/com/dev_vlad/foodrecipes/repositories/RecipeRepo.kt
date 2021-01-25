package com.dev_vlad.foodrecipes.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev_vlad.foodrecipes.api.RecipeApiClient
import com.dev_vlad.foodrecipes.models.Recipe

object RecipeRepo {

    private var currentQuery:String = ""
    private var currentPage :Int = 1

    fun getRecipes() : LiveData<ArrayList<Recipe>> {
        return RecipeApiClient.getRecipes()
    }

    fun searchRecipesApi(query:String, page:Int = 1){
        currentQuery = query
        currentPage = page
        RecipeApiClient.searchRecipesApi(query, page)
    }

    fun fetchNextResults(){
        currentPage += 1
        RecipeApiClient.searchRecipesApi(currentQuery, currentPage)

    }

    fun cancelRequest(){
        RecipeApiClient.cancelRequest()
    }
}