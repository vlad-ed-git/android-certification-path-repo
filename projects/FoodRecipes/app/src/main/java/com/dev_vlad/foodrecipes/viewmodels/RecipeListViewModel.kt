package com.dev_vlad.foodrecipes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev_vlad.foodrecipes.api.RecipeApiClient
import com.dev_vlad.foodrecipes.api.responses.RecipeResponse
import com.dev_vlad.foodrecipes.models.Recipe
import com.dev_vlad.foodrecipes.repositories.RecipeRepo

class RecipeListViewModel : ViewModel() {

    private var isViewingRecipes = false
    private var isPerformingQuery = false

    fun getRecipes() : LiveData<ArrayList<Recipe>>{
         return RecipeRepo.getRecipes()
    }

    fun searchRecipesApi(query:String, page:Int = 1){
        this.isViewingRecipes =  true
        this.isPerformingQuery = true
        RecipeRepo.searchRecipesApi(query, page)
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
        RecipeRepo.cancelRequest()
        isPerformingQuery = false
    }

}