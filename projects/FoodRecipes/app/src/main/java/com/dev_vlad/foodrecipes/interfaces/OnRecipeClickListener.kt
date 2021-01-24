package com.dev_vlad.foodrecipes.interfaces

interface OnRecipeClickListener {

    fun onRecipeClick(position: Int)

    fun onRecipeCategoryClicked(category: String)
}