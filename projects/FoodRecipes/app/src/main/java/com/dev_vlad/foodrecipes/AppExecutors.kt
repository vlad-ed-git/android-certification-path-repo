package com.dev_vlad.foodrecipes

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService


//run background network calls
object AppExecutors {

    //do database operations on background
    private val mDiskIO = Executors.newSingleThreadExecutor()

    fun getDiskIO() : ExecutorService {
        return mDiskIO
    }

    //post data to main thread
    private val mMainThreadExec = MainThreadExecutor()
    class MainThreadExecutor : Executor{
        private val mainThreadHandler : Handler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    fun getMainThreadExec() : Executor = mMainThreadExec

}