package com.dev_vlad.foodrecipes.api.runnables

import com.dev_vlad.foodrecipes.api.RecipeApiClient
import com.dev_vlad.foodrecipes.api.ServiceGenerator
import com.dev_vlad.foodrecipes.api.responses.RecipeSearchResponse
import com.dev_vlad.foodrecipes.models.Recipe
import com.dev_vlad.foodrecipes.util.Constants
import com.dev_vlad.foodrecipes.util.MyLogger
import retrofit2.Call
import java.io.IOException

//the background task(s) that access the network
internal class RetrieveRecipesRunnable(
    private var searchQuery: String, private var pageNumber: Int, var cancelRequest : Boolean = false
) : Runnable{

    private val LOG_TAG = RetrieveRecipesRunnable::class.java.simpleName

    override fun run() {
        try {
            val response = getRecipesFromServer(searchQuery, pageNumber)?.execute()
            //check if user has cancelled Request
            if(cancelRequest){
                MyLogger.logThis(LOG_TAG, "run", "cancelling request...", )
                return
            }

            when{

                response == null -> {
                    MyLogger.logThis(LOG_TAG, "run","response is null")
                    RecipeApiClient.mutableRecipes.postValue(null)
                }

                response.code() == 200 -> {
                    MyLogger.logThis(LOG_TAG, "run","response is successful")
                    val recipeListFetched = (response.body() as RecipeSearchResponse).recipes
                    if (pageNumber == 1){
                        //using postValue not setValue because we are in a background thread
                        RecipeApiClient.mutableRecipes.postValue(recipeListFetched as ArrayList<Recipe>?)
                    }else{
                        //append not set
                        val currentRecipes = RecipeApiClient.mutableRecipes.value
                        currentRecipes?.addAll(recipeListFetched)
                    }


                }

                else -> {
                    MyLogger.logThis(LOG_TAG, "run","response returned code ${response.code()} : ${response.errorBody()}")
                    //to let user know that an error occurred
                    RecipeApiClient.mutableRecipes.postValue(null)
                }

            }


        } catch (e : IOException){
            MyLogger.logThis(LOG_TAG, "run", "caught exception : ${e.message}", e)
            //to let user know that an error occurred
            RecipeApiClient.mutableRecipes.postValue(null)
        }
    }

    //returns the retrofit call object to be enqueued -- or executed by run in this case
    private fun getRecipesFromServer(query: String, page: Int): Call<RecipeSearchResponse?>? {
        return ServiceGenerator.getRecipeApi().searchRecipe(
            Constants.API_KEY,
            query = query,
            page = page.toString()
        )
    }

    fun cancelRequest(){
        MyLogger.logThis(LOG_TAG, location = "cancelRequest()", msg = "User cancelled request")
        cancelRequest = true
    }


}