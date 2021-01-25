package com.dev_vlad.foodrecipes.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev_vlad.foodrecipes.R
import com.dev_vlad.foodrecipes.adapters.viewholders.LoadingViewHolder
import com.dev_vlad.foodrecipes.adapters.viewholders.RecipeCategoryViewHolder
import com.dev_vlad.foodrecipes.adapters.viewholders.RecipeViewHolder
import com.dev_vlad.foodrecipes.interfaces.OnRecipeClickListener
import com.dev_vlad.foodrecipes.models.Recipe
import com.dev_vlad.foodrecipes.util.Constants
import com.dev_vlad.foodrecipes.util.MyLogger
import kotlin.math.roundToInt

//accepts a generic recycler view holder
class RecipeRecyclerAdapter(
    private val clickListener : OnRecipeClickListener
)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        //view types
        private val LOG_TAG = RecipeRecyclerAdapter::class.java.simpleName  
        private const val RECIPE_TYPE = 1
        private const val LOADING_TYPE = 2
        private const val CATEGORY_TYPE = 3
        const val LOADING_TITLE_PLACEHOLDER = "LOADING..."
        const val CATEGORY_TITLE_PLACEHOLDER = "CATEGORY..."
    }

    private lateinit var recipeList : List<Recipe>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //only one for now
        MyLogger.logThis(LOG_TAG, "onCreateViewHolder()", "viewType $viewType")
        when(viewType){
            LOADING_TYPE -> {
                val view  =   LayoutInflater.from(
                    parent.context,
                ).inflate(
                    R.layout.loading_list_item,
                    parent,
                    false
                )
                return LoadingViewHolder(view)
            }
            CATEGORY_TYPE -> {
                val view  =   LayoutInflater.from(
                        parent.context,
                ).inflate(
                        R.layout.recipe_category_list_item,
                        parent,
                        false
                )
                return RecipeCategoryViewHolder(view, clickListener)
            }

            else -> {
                //default is RECIPE_TYPE
                val view  =    LayoutInflater.from(
                    parent.context,
                ).inflate(
                    R.layout.recipe_list_item,
                    parent,
                    false
                )
                return RecipeViewHolder(view, clickListener)
            }

        }



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      val viewType = getItemViewType(position)
        if(viewType == RECIPE_TYPE){
            val recipeViewHolder =  (holder as RecipeViewHolder)
            val recipe = recipeList[position]
            recipeViewHolder.title.text = recipe.title
            recipeViewHolder.publisher.text = recipe.publisher
            recipeViewHolder.socialScore.text = recipe.social_rank.roundToInt().toString()
            Glide.with(holder.itemView.context)
                .load(recipe.image_url)
                .placeholder(R.drawable.placeholder)
                .into(recipeViewHolder.image)
        }

        else if(viewType == CATEGORY_TYPE){
            val recipeCategoryViewHolder =  (holder as RecipeCategoryViewHolder)
            val recipeCategory = recipeList[position]
            val path : Uri =
                    Uri.parse("android.resource://${holder.itemView.context.packageName}/raw/${recipeCategory.image_url}" )
            MyLogger.logThis(LOG_TAG, "onBindViewHolder()", "path : $path")
            Glide.with(holder.itemView.context)
                    .load(path)
                    .circleCrop()
                    .into(recipeCategoryViewHolder.categoryImage)
            recipeCategoryViewHolder.categoryTitle.text = recipeCategory.title
        }
    }

    //since we have multiple types
    override fun getItemViewType(position: Int): Int {
        return when (recipeList[position].recipe_id) {

            LOADING_TITLE_PLACEHOLDER -> {
                LOADING_TYPE
            }
            CATEGORY_TITLE_PLACEHOLDER -> {
                CATEGORY_TYPE
            }
            else -> {
                RECIPE_TYPE
            }
        }

    }


    /*
    *** we manipulate the data by setting a title
    * of recipe as LOADING_TITLE_PLACEHOLDER
     */
    fun displayLoading(){
        if (!isLoading()){
            MyLogger.logThis(LOG_TAG, "displayLoading()", "set loading placeholder recipe data")
            val recipe = Recipe(LOADING_TITLE_PLACEHOLDER)
            val loadingList = arrayListOf<Recipe>()
            loadingList.add(recipe)
            recipeList = loadingList
            notifyDataSetChanged()
        }
    }

    private fun isLoading() : Boolean{

        var isLoading = false
        if(::recipeList.isInitialized && recipeList.isNotEmpty()) {
            isLoading =  (recipeList[recipeList.size - 1].equals(LOADING_TITLE_PLACEHOLDER))
        }
        MyLogger.logThis(LOG_TAG, "isLoading()", "status $isLoading")
        return isLoading
    }

    fun displaySearchCategories(){
        val recipeCategoryList = arrayListOf<Recipe>()
        for ((index, aRecipeCategory) in Constants.DEFAULT_SEARCH_CATEGORIES.withIndex()){
             val recipeCategory = Recipe(recipe_id = CATEGORY_TITLE_PLACEHOLDER, title = aRecipeCategory, image_url = Constants.DEFAULT_SEARCH_CATEGORY_IMAGES[index])
            recipeCategoryList.add(recipeCategory)
        }
        recipeList = recipeCategoryList
        notifyDataSetChanged()


    }

    override fun getItemCount(): Int {
        return if(::recipeList.isInitialized)
            recipeList.size
        else
            0
    }

    fun setRecipes(newRecipeList: List<Recipe>){
        recipeList = newRecipeList
        notifyDataSetChanged()
    }


}