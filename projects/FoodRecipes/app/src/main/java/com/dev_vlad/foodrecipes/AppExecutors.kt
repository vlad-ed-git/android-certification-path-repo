package com.dev_vlad.foodrecipes

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService


//run background network calls
object AppExecutors {

    private val mNetworkIO : ScheduledExecutorService = Executors.newScheduledThreadPool(3)

    fun getNetworkIO() : ScheduledExecutorService{
        return mNetworkIO
    }
}