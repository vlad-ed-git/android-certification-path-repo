package com.dev_vlad.foodrecipes

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dev_vlad.foodrecipes.util.MyLogger
import com.dev_vlad.foodrecipes.viewmodels.RecipeListViewModel

class RecipeListActivity : BaseActivity() {

    companion object{
        private val LOG_TAG = RecipeListActivity::class.java.simpleName
    }

    private lateinit var recipeListViewModel : RecipeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        recipeListViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)
        subscribeToObservers()
        searchRecipesApi()
    }

    private fun subscribeToObservers(){
        recipeListViewModel.getRecipes().observe(this,
        Observer {  recipeList ->
            if (recipeList == null){
                MyLogger.logThis(LOG_TAG, "subscribeToObservers()" , "recipe list is null")
            }else{
                MyLogger.logThis(LOG_TAG, "subscribeToObservers()" , "${recipeList.size} recipes returned")
            }
        })
    }

    private fun searchRecipesApi(){
        //test
        val query = "chicken breast"
        val page = 1
        recipeListViewModel.searchRecipesApi(query, page)
    }

}