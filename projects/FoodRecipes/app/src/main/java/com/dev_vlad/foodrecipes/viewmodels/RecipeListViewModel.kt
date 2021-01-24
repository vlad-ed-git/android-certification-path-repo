package com.dev_vlad.foodrecipes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev_vlad.foodrecipes.api.RecipeApiClient
import com.dev_vlad.foodrecipes.api.responses.RecipeResponse
import com.dev_vlad.foodrecipes.models.Recipe
import com.dev_vlad.foodrecipes.repositories.RecipeRepo

class RecipeListViewModel : ViewModel() {


    fun getRecipes() : LiveData<ArrayList<Recipe>>{
        return RecipeRepo.getRecipes()
    }

    fun searchRecipesApi(query:String, page:Int = 1){
        RecipeRepo.searchRecipesApi(query, page)
    }

}