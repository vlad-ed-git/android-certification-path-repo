package com.dev_vlad.foodrecipes.adapters.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev_vlad.foodrecipes.R
import com.dev_vlad.foodrecipes.interfaces.OnRecipeClickListener
import org.w3c.dom.Text

class RecipeCategoryViewHolder(itemView: View, private val onRecipeClickListener: OnRecipeClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener  {

    val categoryImage: ImageView = itemView.findViewById<ImageView>(R.id.recipe_category_img)
     val categoryTitle: TextView = itemView.findViewById<TextView>(R.id.recipe_category_title)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        onRecipeClickListener.onRecipeCategoryClicked(categoryTitle.text.toString())
    }


}