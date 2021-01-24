package com.dev_vlad.foodrecipes.util

import android.util.Log

object MyLogger {

    private const val DEBUG = true

    fun logThis(tag: String, location:String, msg:String, throwable: Throwable? = null) {
        if (DEBUG) {
            Log.d(tag, "at $location : message $msg", throwable)
        }
    }
}