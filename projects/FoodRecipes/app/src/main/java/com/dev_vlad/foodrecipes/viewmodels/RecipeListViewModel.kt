package com.dev_vlad.foodrecipes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dev_vlad.foodrecipes.models.Recipe
import com.dev_vlad.foodrecipes.repositories.RecipeRepo
import com.dev_vlad.foodrecipes.util.MyLogger

class RecipeListViewModel : ViewModel() {

    companion object{
        private val LOG_TAG =  RecipeListViewModel::class.java.simpleName
    }

    private var isViewingRecipes = false
    private var isPerformingQuery = false
    //to know if the timeout error should be displayed
    var recipesRetrievedSuccessfully = false

    fun getRecipes() : LiveData<List<Recipe>>{
         return RecipeRepo.getRecipes()
    }
    fun hasSearchQueryExhausted(): LiveData<Boolean> = RecipeRepo.hasSearchQueryExhausted()

    fun hasRecipesRequestTimeout() : LiveData<Boolean> {
        return RecipeRepo.hasRecipesRequestTimeout()
    }

    fun searchRecipesApi(query:String, page:Int = 1){
        this.isViewingRecipes =  true
        this.isPerformingQuery = true
        RecipeRepo.searchRecipesApi(query, page)
    }

    fun searchNextPage(){
        if(!isPerformingQuery && isViewingRecipes && !hasSearchQueryExhausted().value!!) {
            RecipeRepo.fetchNextResults()
            MyLogger.logThis(
                    LOG_TAG,
                    "searchNextPage()",
                    "loading for more"
            )
        }
    }

    fun setIsViewingRecipes(isViewingRecipes : Boolean) {
        this.isViewingRecipes = isViewingRecipes
    }

    fun isViewingRecipes() = isViewingRecipes

    fun setIsPerformingQuery(isPerformingQuery : Boolean) {
        this.isPerformingQuery= isPerformingQuery
    }

    fun isPerformingQuery() = isPerformingQuery

    fun cancelRequest(){
        RecipeRepo.cancelSearchRecipesRequest()
        isPerformingQuery = false
    }

}