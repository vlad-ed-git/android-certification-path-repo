package com.dev_vlad.foodrecipes

import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.dev_vlad.foodrecipes.util.HorizontalDottedProgress
import com.dev_vlad.foodrecipes.util.MyLogger
import com.dev_vlad.foodrecipes.util.showSnackBar


//an abstract class
abstract class  BaseActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var containerLayout: FrameLayout
    override fun setContentView(layoutResID: Int) {
        containerLayout = layoutInflater.inflate(R.layout.activity_base, null) as FrameLayout
        val childFrameLayout: FrameLayout = containerLayout.findViewById(R.id.activity_container)
        progressBar = containerLayout.findViewById(R.id.progress_bar)
        layoutInflater.inflate(layoutResID, childFrameLayout, true)
        super.setContentView(containerLayout)
    }

    fun toggleProgressBar(showProgressBar: Boolean){
        if(showProgressBar){
            progressBar.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.GONE
        }

    }

    fun  displaySnackBar(isError : Boolean, msg_res_id : Int){
        containerLayout.showSnackBar(isErrorMsg = isError, msgResId = msg_res_id)
    }
}

