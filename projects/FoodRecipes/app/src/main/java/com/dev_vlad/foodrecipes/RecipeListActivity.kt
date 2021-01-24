package com.dev_vlad.foodrecipes

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev_vlad.foodrecipes.adapters.RecipeRecyclerAdapter
import com.dev_vlad.foodrecipes.interfaces.OnRecipeClickListener
import com.dev_vlad.foodrecipes.util.MyLogger
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
        searchRecipesApi()
    }


    private fun initRecycler(){
        recipeRecyclerAdapter = RecipeRecyclerAdapter(
            this
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recipeRecyclerAdapter
    }

    private fun subscribeToObservers(){
        recipeListViewModel.getRecipes().observe(this,
        Observer {  recipeList ->
            if (recipeList == null){
                MyLogger.logThis(LOG_TAG, "subscribeToObservers()" , "recipe list is null")
            }else{
                MyLogger.logThis(LOG_TAG, "subscribeToObservers()" , "${recipeList.size} recipes returned")
                recipeRecyclerAdapter.setRecipes(recipeList)
            }
        })
    }

    private fun searchRecipesApi(){
        //test
        val query = "chicken breast"
        val page = 1
        recipeListViewModel.searchRecipesApi(query, page)
    }

    override fun onRecipeClick(position: Int) {
        MyLogger.logThis(LOG_TAG, "onRecipeClick" , ": clicked $position")
    }

    override fun onRecipeCategoryClicked(category: String) {
        MyLogger.logThis(LOG_TAG, "onRecipeCategoryClicked" , " $category")
    }

}