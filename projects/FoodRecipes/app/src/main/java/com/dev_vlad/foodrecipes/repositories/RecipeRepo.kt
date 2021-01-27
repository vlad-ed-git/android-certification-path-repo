package com.dev_vlad.foodrecipes.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dev_vlad.foodrecipes.api.RecipeApiClient
import com.dev_vlad.foodrecipes.models.Recipe
import com.dev_vlad.foodrecipes.util.Constants

object RecipeRepo {

    private var currentQuery:String = ""
    private var currentPage :Int = 1

    private val isSearchQueryExhausted : MutableLiveData<Boolean> = MutableLiveData(false)
    //a kind of live data that changes your data before sending it to the observer
    private val mediatedRecipesList : MediatorLiveData<List<Recipe>> = MediatorLiveData()

    init {
        initMediators()
    }

    //we will have more than one mediator later when we setup room
    private fun initMediators(){
        val recipeListApiSource = RecipeApiClient.getRecipes()
        mediatedRecipesList.addSource(recipeListApiSource, Observer {
            //on changed
            recipes ->
            if (recipes != null ) {
                mediatedRecipesList.value = recipes
                doneQuery(recipes)
            }
        })
    }

    private fun doneQuery(recipes : List<Recipe>?){
        if(recipes == null){
            isSearchQueryExhausted.value = true
        }
        else if(recipes.size < Constants.MAX_RESULTS_PAGE) {
            isSearchQueryExhausted.value = true
        }

    }

    fun hasSearchQueryExhausted(): LiveData<Boolean> = isSearchQueryExhausted


    fun getRecipes() : LiveData<List<Recipe>> {
        return mediatedRecipesList
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
        isSearchQueryExhausted.value = false
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