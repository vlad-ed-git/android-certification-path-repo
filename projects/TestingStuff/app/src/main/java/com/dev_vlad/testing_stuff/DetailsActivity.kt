package com.dev_vlad.testing_stuff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev_vlad.testing_stuff.databinding.ActivityDetailsBinding
import com.dev_vlad.testing_stuff.databinding.ActivityMainBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.backBtn.setOnClickListener {
            onBackPressed()
        }

    }
}