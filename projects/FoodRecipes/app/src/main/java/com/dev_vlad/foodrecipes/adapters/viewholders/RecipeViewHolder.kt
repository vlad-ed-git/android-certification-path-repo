package com.dev_vlad.foodrecipes.adapters.viewholders

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.dev_vlad.foodrecipes.R
import com.dev_vlad.foodrecipes.interfaces.OnRecipeClickListener



class RecipeViewHolder(itemView: View, private val OnRecipeClickListener: OnRecipeClickListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {


    //references the recipe list item
    val title: TextView = itemView.findViewById(R.id.recipe_title)
    val publisher: TextView = itemView.findViewById(R.id.recipe_publisher)
    val socialScore: TextView = itemView.findViewById(R.id.recipe_social_score)
    val image: AppCompatImageView = itemView.findViewById(R.id.recipe_image)

    override fun onClick(v: View) {
        OnRecipeClickListener.onRecipeClick(adapterPosition)
    }

    init {
        itemView.setOnClickListener(this)
    }
}