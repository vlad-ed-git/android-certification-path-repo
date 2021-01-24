package com.dev_vlad.foodrecipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev_vlad.foodrecipes.R
import com.dev_vlad.foodrecipes.adapters.viewholders.RecipeViewHolder
import com.dev_vlad.foodrecipes.interfaces.OnRecipeClickListener
import com.dev_vlad.foodrecipes.models.Recipe
import kotlin.math.roundToInt
import kotlin.math.roundToLong

//accepts a generic recycler view holder
class RecipeRecyclerAdapter(
    private var recipeList : List<Recipe>,
    private val clickListener : OnRecipeClickListener
)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //only one for now
        val view = LayoutInflater.from(
            parent.context,
        ).inflate(
            R.layout.recipe_list_item,
            parent,
            false
        )
        return RecipeViewHolder(view, clickListener)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      val recipeViewHolder =  (holder as RecipeViewHolder)
        val recipe = recipeList[position]
        recipeViewHolder.title.text = recipe.title
        recipeViewHolder.publisher.text = recipe.publisher
        recipeViewHolder.socialScore.text = recipe.social_rank.roundToInt().toString()
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    fun setRecipes(newRecipeList: List<Recipe>){
        recipeList = newRecipeList
        notifyDataSetChanged()
    }


}