package com.dev_vlad.foodrecipes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev_vlad.foodrecipes.models.Recipe

class RecipeListViewModel : ViewModel() {

    //live-data
    private val mutableRecipes : MutableLiveData<List<Recipe>> = MutableLiveData()


    fun getRecipes() : LiveData<List<Recipe>>{
        return mutableRecipes
    }

}