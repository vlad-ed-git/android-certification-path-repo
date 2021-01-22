package com.dev_vlad.foodrecipes.models

import android.os.Parcel
import android.os.Parcelable

data class Recipe(
    private var title: String,
    private var publisher: String,
    private var ingredients: Array<String>,
    private var recipe_id: String,
    private var image_url: String,
    private var social_rank: Float
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArray()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(publisher)
        parcel.writeStringArray(ingredients)
        parcel.writeString(recipe_id)
        parcel.writeString(image_url)
        parcel.writeFloat(social_rank)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Recipe

        if (title != other.title) return false
        if (publisher != other.publisher) return false
        if (!ingredients.contentEquals(other.ingredients)) return false
        if (recipe_id != other.recipe_id) return false
        if (image_url != other.image_url) return false
        if (social_rank != other.social_rank) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + publisher.hashCode()
        result = 31 * result + ingredients.contentHashCode()
        result = 31 * result + recipe_id.hashCode()
        result = 31 * result + image_url.hashCode()
        result = 31 * result + social_rank.hashCode()
        return result
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}