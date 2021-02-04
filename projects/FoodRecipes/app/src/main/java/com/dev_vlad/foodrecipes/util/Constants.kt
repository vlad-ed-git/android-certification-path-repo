package com.dev_vlad.foodrecipes.util

object Constants {

    const val MAX_RESULTS_PAGE: Int = 30
    const val BASE_URL = "https://recipesapi.herokuapp.com"

    const val API_KEY = "" //not required
    const val NETWORK_TIMEOUT = 3000L
    val DEFAULT_SEARCH_CATEGORIES =
        arrayOf("Barbeque", "Breakfast", "Chicken", "Beef", "Brunch", "Dinner", "Wine", "Italian")
    val DEFAULT_SEARCH_CATEGORY_IMAGES = arrayOf(
        "barbeque",
        "breakfast",
        "chicken",
        "beef",
        "brunch",
        "dinner",
        "wine",
        "italian"
    )
}