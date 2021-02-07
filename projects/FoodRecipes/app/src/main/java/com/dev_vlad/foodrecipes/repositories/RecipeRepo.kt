package com.dev_vlad.foodrecipes.repositories

import androidx.lifecycle.LiveData
import com.dev_vlad.foodrecipes.api.ServiceGenerator.getRecipeApi
import com.dev_vlad.foodrecipes.api.responses.RecipeResponse
import com.dev_vlad.foodrecipes.api.responses.RecipeSearchResponse
import com.dev_vlad.foodrecipes.models.Recipe
import com.dev_vlad.foodrecipes.persistence.RecipeDao
import com.dev_vlad.foodrecipes.util.Constants
import com.dev_vlad.foodrecipes.util.network_resources.GenericApiResponse
import com.dev_vlad.foodrecipes.util.network_resources.NetworkBoundResource
import com.dev_vlad.foodrecipes.util.network_resources.Resource


object RecipeRepository  {

    fun searchRecipesApi(recipeDao: RecipeDao, query: String?, pageNumber: Int): LiveData<Resource<List<Recipe?>?>> {
        return object :
            NetworkBoundResource<List<Recipe?>?, RecipeSearchResponse?>() {

            override fun saveCallResult(item: RecipeSearchResponse?) {
                if (item != null) {
                    val recipes = arrayOfNulls<Recipe>(item.recipes.size)
                    val recipesToInsert =  item.recipes.toList()
                    for ((index, rowid) in recipeDao.insertRecipes(recipesToInsert)!!.withIndex()) {
                        if (rowid == -1L) {

                            // if the recipe already exists... I don't want to set the ingredients or timestamp b/c
                            // they will be erased
                            recipeDao.updateRecipe(
                                recipes[index]!!.recipe_id,
                                recipes[index]!!.title,
                                recipes[index]!!.publisher,
                                recipes[index]!!.image_url,
                                recipes[index]!!.social_rank
                            )
                        }
                    }
                }
            }

            override fun shouldFetch(data: List<Recipe?>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Recipe?>?> {
                return recipeDao.searchRecipes(query, pageNumber)
            }

            override fun createCall(): LiveData<GenericApiResponse<RecipeSearchResponse?>> {
                return getRecipeApi()
                    .searchRecipe(
                        Constants.API_KEY,
                        query, pageNumber.toString()
                    )
            }
        }.resultsAsLiveData()
    }

    fun getRecipeApi(recipeDao: RecipeDao, recipeId: String?): LiveData<Resource<Recipe?>> {
        return object : NetworkBoundResource<Recipe?, RecipeResponse?>() {
            override fun saveCallResult(item: RecipeResponse?) {

                // will be null if API key is expired
                if (item?.getRecipe() != null) {
                    item.getRecipe()!!.timestamp = (System.currentTimeMillis() / 1000).toInt()
                    recipeDao.insertRecipe(item.getRecipe())
                }
            }

            override fun shouldFetch(data: Recipe?): Boolean {
                val currentTime = (System.currentTimeMillis() / 1000).toInt()
                val lastRefresh = data!!.timestamp
                if (currentTime - data.timestamp >= Constants.RECIPE_REFRESH_TIME) {
                    return true
                }
                return false
            }

            override fun loadFromDb(): LiveData<Recipe?> {
                return recipeDao.getRecipe(recipeId)
            }

            override fun createCall(): LiveData<GenericApiResponse<RecipeResponse?>> {
                return getRecipeApi().getRecipe(
                    Constants.API_KEY,
                    recipeId
                )
            }
        }.resultsAsLiveData()
    }

}




