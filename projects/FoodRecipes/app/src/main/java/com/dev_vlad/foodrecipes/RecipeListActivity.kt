package com.dev_vlad.foodrecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RecipeListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        toggleProgressBar(true)
    }
}