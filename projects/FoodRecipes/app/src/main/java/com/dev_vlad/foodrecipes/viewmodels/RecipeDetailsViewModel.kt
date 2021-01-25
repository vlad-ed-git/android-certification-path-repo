package com.dev_vlad.foodrecipes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dev_vlad.foodrecipes.models.Recipe
import com.dev_vlad.foodrecipes.repositories.RecipeRepo
import com.dev_vlad.foodrecipes.util.MyLogger

class RecipeDetailsViewModel : ViewModel() {

    companion object{
        private val LOG_TAG =  RecipeDetailsViewModel::class.java.simpleName
    }

    private var isPerformingQuery = false

    fun getRecipe() : LiveData<Recipe> {
        return RecipeRepo.getARecipe()
    }

    fun searchRecipeByIdApi(recipeId:String){
        this.isPerformingQuery = true
        RecipeRepo.searchRecipeByIdApi(recipeId = recipeId)
    }


    fun setIsPerformingQuery(isPerformingQuery : Boolean) {
        this.isPerformingQuery= isPerformingQuery
    }


    fun cancelGetRecipeRequest(){
        if(isPerformingQuery) {
            RecipeRepo.cancelGetRecipeRequest()
            isPerformingQuery = false
        }
    }
}