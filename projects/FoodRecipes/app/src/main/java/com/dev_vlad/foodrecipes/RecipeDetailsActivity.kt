package com.dev_vlad.foodrecipes

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dev_vlad.foodrecipes.models.Recipe
import com.dev_vlad.foodrecipes.util.MyLogger
import com.dev_vlad.foodrecipes.viewmodels.RecipeDetailsViewModel

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

    private lateinit var recipeDetailsViewModel: RecipeDetailsViewModel
    private lateinit var theRecipe : Recipe
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        recipeDetailsViewModel =  ViewModelProvider(this).get(RecipeDetailsViewModel::class.java)
        recipeImageView = findViewById(R.id.recipe_image)
        ingredientsContainer = findViewById(R.id.ingredients_container)
        recipeTitle = findViewById(R.id.recipe_title)
        recipeRank = findViewById(R.id.recipe_social_score)
        parentContainer = findViewById(R.id.parent_container)


        toggleProgressBar(true)
        if(intent.hasExtra(RECIPE_INTENT)){
            val recipe =  intent.getParcelableExtra<Recipe>(RECIPE_INTENT)
            recipe?.let {
                theRecipe = it
                recipeDetailsViewModel.searchRecipeByIdApi(recipeId = theRecipe.recipe_id)

            }
        }
        subscribeObservers()
    }

    private fun subscribeObservers(){
        recipeDetailsViewModel.getRecipe().observe(this, Observer { fetchedRecipe ->

            if (fetchedRecipe != null){
                //we make sure that the recipe that this observer is showing us
                //is not the one it saved before the search request is made
                //from when we previously run this activity
                //DO NOT DISPLAY PREV VM CACHED RECIPE
                if (recipeDetailsViewModel.getRecipeId()
                    == fetchedRecipe.recipe_id
                ) {
                    theRecipe = fetchedRecipe
                    displayTheRecipe()
                }

            }else{
                //display the passed info from intent
                displayTheRecipe()
                Toast.makeText(
                    this,
                    R.string.couldnt_load_ingredients,
                    Toast.LENGTH_LONG
                ).show()
            }


        })
    }
    
    private fun displayTheRecipe(){
       recipeDetailsViewModel.setIsPerformingQuery(false)
        Glide.with(this).load(theRecipe.image_url).placeholder(R.drawable.placeholder).into(recipeImageView)
        recipeTitle.text = theRecipe.title
        recipeRank.text = theRecipe.social_rank.toString()
        setIngredients()
        parentContainer.visibility = View.VISIBLE
        toggleProgressBar( false)

    }



    private fun setIngredients(){
        //remove any that were previously in there
        ingredientsContainer.removeAllViews()
        theRecipe.ingredients?.let { ingredients ->

            for (ingredient in ingredients){
                val txtV : TextView = TextView(this)
                txtV.text = ingredient
                txtV.textSize = 16F
                txtV.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                ingredientsContainer.addView(txtV)
            }

        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        recipeDetailsViewModel.cancelGetRecipeRequest()
    }

}