package com.dev_vlad.foodrecipes.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dev_vlad.foodrecipes.models.Recipe
import com.dev_vlad.foodrecipes.persistence.getRecipeDao
import com.dev_vlad.foodrecipes.repositories.RecipeRepository
import com.dev_vlad.foodrecipes.util.network_resources.Resource

class RecipeListViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        private val LOG_TAG = RecipeListViewModel::class.java.simpleName
    }

    enum class ViewState {
        DISPLAYING_CATEGORIES,
        DISPLAYING_RECIPES
    }


    private val recipeDao = getRecipeDao(applicationContext = application.applicationContext)

    private val viewState = MutableLiveData<ViewState>(ViewState.DISPLAYING_CATEGORIES)

    fun getViewState(): LiveData<ViewState> = viewState

    private val observableRecipes = MediatorLiveData<Resource<List<Recipe?>?>>()

    fun getRecipes(): LiveData<Resource<List<Recipe?>?>> = observableRecipes

    fun searchRecipesApi(query: String, pageNumber: Int) {
        if (!isPerformingQuery) {
            currentPage = pageNumber
            currentQuery = query
            isQueryExhausted = false
            executeSearch()
        }

    }

    private fun executeSearch() {
        isPerformingQuery = true
        viewState.value = ViewState.DISPLAYING_RECIPES
        val repositorySource: LiveData<Resource<List<Recipe?>?>> =
            RecipeRepository.searchRecipesApi(
                recipeDao = recipeDao,
                query = currentQuery,
                pageNumber = currentPage
            )

        observableRecipes.addSource(repositorySource) { listResource ->

            isPerformingQuery = false
            if (listResource != null) {
                observableRecipes.value = listResource
                if (listResource.status == Resource.Status.SUCCESS) {
                    if (listResource.data != null) {
                        if (listResource.data.isEmpty()) {
                            isQueryExhausted = true
                            //notify observer
                            observableRecipes.value = Resource(
                                status = Resource.Status.ERROR,
                                data = listResource.data,
                                message = QUERY_IS_EXHAUSTED_MSG
                            )
                        }
                    }

                    observableRecipes.removeSource(repositorySource)
                } else if (listResource.status == Resource.Status.ERROR) {

                    observableRecipes.removeSource(repositorySource)
                }
            } else {
                //stop observing
                //the returned resource is null
                observableRecipes.removeSource(repositorySource)
            }
        }
    }

    //query trackers
    var isQueryExhausted = false
    var isPerformingQuery = false
    var currentPage: Int = 1
    var currentQuery = ""
    var QUERY_IS_EXHAUSTED_MSG = "NO MORE RECIPES FOUND"

}