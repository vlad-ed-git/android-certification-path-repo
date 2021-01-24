package com.dev_vlad.foodrecipes.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev_vlad.foodrecipes.models.Recipe

/* used by recipe repo to get data from network */
object RecipeApiClient {

    //live-data
    private val mutableRecipes : MutableLiveData<List<Recipe>> = MutableLiveData()


    fun getRecipes() : LiveData<List<Recipe>> {
        return mutableRecipes
    }
}