package com.dev_vlad.foodrecipes.api.runnables

import com.dev_vlad.foodrecipes.api.RecipeApiClient
import com.dev_vlad.foodrecipes.api.ServiceGenerator
import com.dev_vlad.foodrecipes.api.responses.RecipeResponse
import com.dev_vlad.foodrecipes.api.responses.RecipeSearchResponse
import com.dev_vlad.foodrecipes.models.Recipe
import com.dev_vlad.foodrecipes.util.Constants
import com.dev_vlad.foodrecipes.util.MyLogger
import retrofit2.Call
import java.io.IOException

internal class RetrieveARecipeRunnable (
    private var recipeId:String, var cancelRequest : Boolean = false
) : Runnable{

    private val LOG_TAG = RetrieveARecipeRunnable::class.java.simpleName

    override fun run() {
        try {
            val response = getRecipeFromServer(recipeId)?.execute()
            //check if user has cancelled Request
            if(cancelRequest){
                MyLogger.logThis(LOG_TAG, "run", "cancelling request...", )
                return
            }

            when{

                response == null -> {
                    MyLogger.logThis(LOG_TAG, "run","response is null")
                    RecipeApiClient.mutableRecipe.postValue(null)
                }

                response.code() == 200 -> {
                    MyLogger.logThis(LOG_TAG, "run","response is successful")
                    val recipe = (response.body() as RecipeResponse).getRecipe()

                    //using postValue not setValue because we are in a background thread
                    RecipeApiClient.mutableRecipe.postValue(recipe)

                }

                else -> {
                    MyLogger.logThis(LOG_TAG, "run","response returned code ${response.code()} : ${response.errorBody()}")
                    //to let user know that an error occurred
                    RecipeApiClient.mutableRecipe.postValue(null)
                }

            }


        } catch (e : IOException){
            MyLogger.logThis(LOG_TAG, "run", "caught exception : ${e.message}", e)
            //to let user know that an error occurred
            RecipeApiClient.mutableRecipe.postValue(null)
        }
    }

    //returns the retrofit call object to be enqueued -- or executed by run in this case
    private fun getRecipeFromServer(recipeId: String): Call<RecipeResponse?>? {
        return ServiceGenerator.getRecipeApi().getRecipe(
            Constants.API_KEY,
            recipe_id = recipeId
        )
    }

    fun cancelRequest(){
        MyLogger.logThis(LOG_TAG, location = "cancelRequest()", msg = "User cancelled request")
        cancelRequest = true
    }


}