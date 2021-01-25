package com.dev_vlad.foodrecipes.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev_vlad.foodrecipes.AppExecutors
import com.dev_vlad.foodrecipes.api.responses.RecipeSearchResponse
import com.dev_vlad.foodrecipes.api.runnables.RetrieveARecipeRunnable
import com.dev_vlad.foodrecipes.api.runnables.RetrieveRecipesRunnable
import com.dev_vlad.foodrecipes.models.Recipe
import com.dev_vlad.foodrecipes.util.Constants
import com.dev_vlad.foodrecipes.util.Constants.NETWORK_TIMEOUT
import com.dev_vlad.foodrecipes.util.MyLogger
import retrofit2.Call
import java.io.IOException
import java.util.concurrent.TimeUnit


/* used by recipe repo to get data from network */
object RecipeApiClient {

    //live-data
    val mutableRecipes : MutableLiveData<ArrayList<Recipe>> = MutableLiveData()
    val mutableRecipe : MutableLiveData<Recipe> = MutableLiveData()

    fun getRecipes() : LiveData<ArrayList<Recipe>> {
        return mutableRecipes
    }

    fun getRecipe() : LiveData<Recipe> {
        return mutableRecipe
    }


    //a runnable for searching
    private var retrieveRecipesRunnable : RetrieveRecipesRunnable? = null
    private var retrieveRecipeRunnable : RetrieveARecipeRunnable? = null


    fun searchRecipesApi(query: String, page: Int){

        //create a recipes runnable
        if (retrieveRecipesRunnable != null)
            retrieveRecipesRunnable = null //should be null to start a new runnable

        retrieveRecipesRunnable = RetrieveRecipesRunnable(
            searchQuery = query, pageNumber = page
        )

        //submit to background
        val handler = AppExecutors.getNetworkIO().submit(retrieveRecipesRunnable!!)

        //cancel the handler task after set timeout
       AppExecutors.getNetworkIO().schedule(
           Runnable {
               //TODO let user know when the request timed out
               handler.cancel(true)
           }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS
       )
    }



    fun searchRecipeByIdApi(recipeId : String){

        //create a recipe runnable
        if (retrieveRecipeRunnable != null)
            retrieveRecipeRunnable = null //should be null to start a new runnable

        retrieveRecipeRunnable = RetrieveARecipeRunnable(
            recipeId = recipeId
        )

        //submit to background
        val handler = AppExecutors.getNetworkIO().submit(retrieveRecipeRunnable!!)

        //cancel the handler task after set timeout
        AppExecutors.getNetworkIO().schedule(
            Runnable {
                //TODO let user know when the request timed out
                handler.cancel(true)
            }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS
        )
    }

    fun cancelSearchRecipesRequest() {
        if(retrieveRecipesRunnable != null){
            retrieveRecipesRunnable?.cancelRequest()
        }
    }

    fun cancelGetRecipeRequest() {
        if(retrieveRecipeRunnable != null){
            retrieveRecipeRunnable?.cancelRequest()
        }
    }


}