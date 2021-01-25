package com.dev_vlad.foodrecipes

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.dev_vlad.foodrecipes.models.Recipe
import com.dev_vlad.foodrecipes.util.MyLogger

class RecipeDetailsActivity : BaseActivity() {

    private lateinit var recipeImageView: ImageView
    private lateinit var ingredientsContainer : LinearLayout
    private lateinit var recipeTitle : TextView
    private lateinit var recipeRank : TextView
    private lateinit var parentContainer: ScrollView



    companion object{
        const val RECIPE_INTENT = "recipe"
        private val LOG_TAG = RecipeDetailsActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        recipeImageView = findViewById(R.id.recipe_image)
        ingredientsContainer = findViewById(R.id.ingredients_container)
        recipeTitle = findViewById(R.id.recipe_title)
        recipeRank = findViewById(R.id.recipe_social_score)
        parentContainer = findViewById(R.id.parent_container)

        if(intent.hasExtra(RECIPE_INTENT)){
            val recipe =  intent.getParcelableExtra<Recipe>(RECIPE_INTENT)
            
        }
    }


}