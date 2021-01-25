package com.dev_vlad.foodrecipes.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev_vlad.foodrecipes.AppExecutors
import com.dev_vlad.foodrecipes.api.responses.RecipeSearchResponse
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
    private val mutableRecipes : MutableLiveData<ArrayList<Recipe>> = MutableLiveData()


    //a runnable for searching
    private var retrieveRecipesRunnable : RetrieveRecipesRunnable? = null

    fun getRecipes() : LiveData<ArrayList<Recipe>> {
        return mutableRecipes
    }

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

    fun cancelRequest() {
        if(retrieveRecipesRunnable != null){
            retrieveRecipesRunnable?.cancelRequest()
        }
    }

    //the background task(s) that access the network
  private class RetrieveRecipesRunnable(
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
                    mutableRecipes.postValue(null)
                }

                response.code() == 200 -> {
                    MyLogger.logThis(LOG_TAG, "run","response is successful")
                    val recipeListFetched = (response.body() as RecipeSearchResponse).recipes
                    if (pageNumber == 1){
                        //using postValue not setValue because we are in a background thread
                        mutableRecipes.postValue(recipeListFetched as ArrayList<Recipe>?)
                    }else{
                        //append not set
                        val currentRecipes = mutableRecipes.value
                        currentRecipes?.addAll(recipeListFetched)
                    }


                }

                else -> {
                    MyLogger.logThis(LOG_TAG, "run","response returned code ${response.code()} : ${response.errorBody()}")
                    //to let user know that an error occurred
                    mutableRecipes.postValue(null)
                }

            }


        } catch (e : IOException){
            MyLogger.logThis(LOG_TAG, "run", "caught exception : ${e.message}", e)
            //to let user know that an error occurred
            mutableRecipes.postValue(null)
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
}