package com.dev_vlad.foodrecipes

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev_vlad.foodrecipes.adapters.RecipeRecyclerAdapter
import com.dev_vlad.foodrecipes.interfaces.OnRecipeClickListener
import com.dev_vlad.foodrecipes.util.MyLogger
import com.dev_vlad.foodrecipes.util.VerticalSpacingItemDecorator
import com.dev_vlad.foodrecipes.viewmodels.RecipeListViewModel

class RecipeListActivity : BaseActivity() , OnRecipeClickListener {

    companion object{
        private val LOG_TAG = RecipeListActivity::class.java.simpleName
    }

    private lateinit var recipeListViewModel : RecipeListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeRecyclerAdapter: RecipeRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        recyclerView = findViewById(R.id.recipe_rv)
        recipeListViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

        initRecycler()
        subscribeToObservers()
        initSearchView()
        if (!recipeListViewModel.isViewingRecipes()){
            displaySearchCategories()
        }
    }


    private fun initRecycler(){
        recipeRecyclerAdapter = RecipeRecyclerAdapter(
            this
        )
        val verticalSpacingItemDecorator = VerticalSpacingItemDecorator(30)
        recyclerView.addItemDecoration(verticalSpacingItemDecorator)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recipeRecyclerAdapter

    }

    private fun subscribeToObservers(){
        recipeListViewModel.getRecipes().observe(this,
        Observer {  recipeList ->
            recipeListViewModel.setIsViewingRecipes(true)
            if (recipeList == null){
                MyLogger.logThis(LOG_TAG, "subscribeToObservers()" , "recipe list is null")
            }else{
                MyLogger.logThis(LOG_TAG, "subscribeToObservers()" , "${recipeList.size} recipes returned")
                recipeRecyclerAdapter.setRecipes(recipeList)
            }
        })
    }


    override fun onRecipeClick(position: Int) {
        MyLogger.logThis(LOG_TAG, "onRecipeClick" , ": clicked $position")
    }

    override fun onRecipeCategoryClicked(category: String) {
        MyLogger.logThis(LOG_TAG, "onRecipeCategoryClicked" , " $category")
        recipeRecyclerAdapter.displayLoading()
        recipeListViewModel.searchRecipesApi(category, page = 1)
    }

    private fun displaySearchCategories(){
        recipeRecyclerAdapter.displaySearchCategories()
    }


    /*********** SEARCH VIEW  **/
    private fun initSearchView(){
       val searchView : SearchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    recipeRecyclerAdapter.displayLoading()
                    recipeListViewModel.searchRecipesApi(it, page = 1)
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
        if (recipeListViewModel.isViewingRecipes()){
              recipeListViewModel.setIsViewingRecipes(false)
             displaySearchCategories()
        }else{
            //they are viewing categories
            super.onBackPressed()
        }
    }

}