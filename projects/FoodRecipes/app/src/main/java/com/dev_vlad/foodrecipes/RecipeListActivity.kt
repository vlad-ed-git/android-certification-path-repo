package com.dev_vlad.foodrecipes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev_vlad.foodrecipes.adapters.RecipeRecyclerAdapter
import com.dev_vlad.foodrecipes.interfaces.OnRecipeClickListener
import com.dev_vlad.foodrecipes.models.Recipe
import com.dev_vlad.foodrecipes.util.MyLogger
import com.dev_vlad.foodrecipes.util.VerticalSpacingItemDecorator
import com.dev_vlad.foodrecipes.viewmodels.RecipeListViewModel
import com.google.android.material.snackbar.Snackbar

class RecipeListActivity : BaseActivity() , OnRecipeClickListener {

    companion object{
        private val LOG_TAG = RecipeListActivity::class.java.simpleName
    }

    private lateinit var recipeListViewModel : RecipeListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeRecyclerAdapter: RecipeRecyclerAdapter
    private lateinit var searchView : SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        recyclerView = findViewById(R.id.recipe_rv)
        recipeListViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

        initRecycler()
        subscribeToObservers()
        initSearchView()
    }


    private fun initRecycler(){
        recipeRecyclerAdapter = RecipeRecyclerAdapter(
            this
        )
        val verticalSpacingItemDecorator = VerticalSpacingItemDecorator(30)
        recyclerView.addItemDecoration(verticalSpacingItemDecorator)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recipeRecyclerAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(rv: RecyclerView, newState: Int) {
               if(!recyclerView.canScrollVertically(1)){
                   //this if will trigger if it is at the bottom
                   //TODO
                   MyLogger.logThis(
                           LOG_TAG,
                           "onScrollStateChanged()",
                           "loading for more"
                   )
               }
            }
        })
    }



    private fun subscribeToObservers(){
        recipeListViewModel.getViewState().observe(
            this, Observer {
                viewState ->
                when(viewState){

                    RecipeListViewModel.ViewState.DISPLAYING_CATEGORIES -> {
                        displaySearchCategories()
                    }
                    RecipeListViewModel.ViewState.DISPLAYING_RECIPES -> {
                        //TODO recipes will be displayed from another observer
                    }
                    else -> displaySearchCategories()
                }
            }
        )

        recipeListViewModel.getRecipes().observe(
            this, { listResource ->

                val recipesList = listResource.data
                if (recipesList == null){
                    MyLogger.logThis(
                    LOG_TAG,
                      "subscribeToObservers : getRecipes()",
                      "resource msg & status ${listResource.message} + ${listResource.status} "
                    )
                }
                else{
                    MyLogger.logThis(
                        LOG_TAG,
                        "subscribeToObservers : getRecipes()",
                        recipesList.toString()
                    )
                    recipeRecyclerAdapter.setRecipes(recipesList)
                }
            }
        )
    }


    override fun onRecipeClick(clickedRecipePosition : Int) {
        val clickedRecipe = recipeRecyclerAdapter.getClickedIfRecipeAtPos(clickedRecipePosition)
        clickedRecipe?.let {
            MyLogger.logThis(LOG_TAG, "onRecipeClick" , ": clicked $clickedRecipe")
            startActivity(Intent(this, RecipeDetailsActivity::class.java).putExtra(RecipeDetailsActivity.RECIPE_INTENT, clickedRecipe))
        }


    }

    override fun onRecipeCategoryClicked(category: String) {
        MyLogger.logThis(LOG_TAG, "onRecipeCategoryClicked" , " $category")
        recipeRecyclerAdapter.displayLoading()
        recipeListViewModel.searchRecipesApi(category, pageNumber = 1)
        searchView.clearFocus()
    }

    private fun displaySearchCategories(){
        recipeRecyclerAdapter.displaySearchCategories()
    }


    /*********** SEARCH VIEW  **/
    private fun initSearchView(){
       searchView  = findViewById(R.id.search_view)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    recipeRecyclerAdapter.displayLoading()
                    recipeListViewModel.searchRecipesApi(it, pageNumber = 1)
                    searchView.clearFocus()//so when user presses back button, that event is not consumed by this focus
                    MyLogger.logThis(LOG_TAG, "onQueryTextSubmit", "query : $query" )
                }
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false //not interested
            }

        })
    }


    override fun onBackPressed() {
        //TODO
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.recipe_search_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_categories){
            //TODO
        }

        return super.onOptionsItemSelected(item)
    }

}