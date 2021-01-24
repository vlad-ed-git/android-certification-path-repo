package com.dev_vlad.foodrecipes.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev_vlad.foodrecipes.models.Recipe

object RecipeRepo {

    //live-data
    private val mutableRecipes : MutableLiveData<List<Recipe>> = MutableLiveData()

    fun getRecipes() : LiveData<List<Recipe>> {
        return mutableRecipes
    }

}