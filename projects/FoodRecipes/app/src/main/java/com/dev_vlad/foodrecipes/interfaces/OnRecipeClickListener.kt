package com.dev_vlad.foodrecipes.interfaces


interface OnRecipeClickListener {

    fun onRecipeClick(clickedRecipePosition: Int)

    fun onRecipeCategoryClicked(category: String)
}