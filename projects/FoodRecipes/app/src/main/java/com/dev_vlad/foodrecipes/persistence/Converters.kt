package com.dev_vlad.foodrecipes.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/*
*** Allows us to save an array like object as string in room
 */
object Converters {
    @TypeConverter
    fun fromString(value: String): Array<String> {
        val listType = object : TypeToken<Array<String>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: Array<String>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}