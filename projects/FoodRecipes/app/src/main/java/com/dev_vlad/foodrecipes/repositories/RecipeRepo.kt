package com.dev_vlad.foodrecipes.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev_vlad.foodrecipes.api.RecipeApiClient
import com.dev_vlad.foodrecipes.models.Recipe

object RecipeRepo {

    fun getRecipes() : LiveData<List<Recipe>> {
        return RecipeApiClient.getRecipes()
    }

}