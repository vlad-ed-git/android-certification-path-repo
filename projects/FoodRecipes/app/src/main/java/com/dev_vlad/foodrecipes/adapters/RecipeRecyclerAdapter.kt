package com.dev_vlad.foodrecipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev_vlad.foodrecipes.R
import com.dev_vlad.foodrecipes.adapters.viewholders.LoadingViewHolder
import com.dev_vlad.foodrecipes.adapters.viewholders.RecipeViewHolder
import com.dev_vlad.foodrecipes.interfaces.OnRecipeClickListener
import com.dev_vlad.foodrecipes.models.Recipe
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
        private const val LOADING_TYPE = 2;
        const val LOADING_TITLE_PLACEHOLDER = "LOADING..."
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
    }

    //since we have multiple types
    override fun getItemViewType(position: Int): Int {
        return if(recipeList[position].equals(LOADING_TITLE_PLACEHOLDER)){
            LOADING_TYPE
        }else{
            RECIPE_TYPE
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