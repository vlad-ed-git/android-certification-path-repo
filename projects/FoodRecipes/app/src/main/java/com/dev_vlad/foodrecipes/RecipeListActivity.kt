package com.dev_vlad.foodrecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dev_vlad.foodrecipes.api.ServiceGenerator
import com.dev_vlad.foodrecipes.api.responses.RecipeResponse
import com.dev_vlad.foodrecipes.api.responses.RecipeSearchResponse
import com.dev_vlad.foodrecipes.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeListActivity : BaseActivity() {

    companion object{
        private val LOG_TAG = RecipeListActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        testRetrofitRequest()

    }

    fun testRetrofitRequest(){
      val recipeApi =   ServiceGenerator.getRecipeApi()
      val responseCall : Call<RecipeResponse?>? =
              recipeApi.getRecipe(
                      Constants.API_KEY,
                      recipe_id = "8c0314"
              )
        responseCall?.enqueue(
                object : Callback<RecipeResponse?>{
                    override fun onResponse(call: Call<RecipeResponse?>, response: Response<RecipeResponse?>) {
                        Log.d(LOG_TAG, response.code().toString())
                        if(response.code() == 200 ){
                            Log.d(LOG_TAG, "onResponse ${response.body()}")
                        }else{
                            Log.d(LOG_TAG, "${response.errorBody()}")
                        }
                    }

                    override fun onFailure(call: Call<RecipeResponse?>, t: Throwable) {
                        Log.d(LOG_TAG, "onFailure ${t.message}", t)
                    }

                }
        )
    }
}