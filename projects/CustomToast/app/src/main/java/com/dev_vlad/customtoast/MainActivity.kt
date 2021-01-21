package com.dev_vlad.customtoast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        CustomToast.showToast(
            this,
            message = getString(R.string.bad_internet_connection),
            image_res = R.drawable.sad
        )
    }
}