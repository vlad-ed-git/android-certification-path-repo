package com.dev_vlad.foodrecipes.util

import android.util.Log

object MyLogger {

    private const val DEBUG = true
    private const val LOG_TAG_PREFIX = "MyLogger"

    fun logThis(tag: String, location:String, msg:String, throwable: Throwable? = null) {
        throwable?.printStackTrace()
        if (DEBUG) {
            Log.d("${LOG_TAG_PREFIX}_$tag", "at $location : message $msg", throwable)
        }
    }
}