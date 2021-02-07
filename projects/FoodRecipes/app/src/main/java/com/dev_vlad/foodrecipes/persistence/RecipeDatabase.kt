package com.dev_vlad.foodrecipes.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dev_vlad.foodrecipes.models.Recipe


@Database(entities = [Recipe::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao


}
const val DATABASE_NAME = "recipe_database"
fun getRecipeDao(applicationContext : Context) : RecipeDao{
    return  Room.databaseBuilder(
        applicationContext,
        RecipeDatabase::class.java, DATABASE_NAME
    ).build()
        .recipeDao()
}