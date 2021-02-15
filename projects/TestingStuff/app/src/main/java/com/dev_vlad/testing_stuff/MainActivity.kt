package com.dev_vlad.testing_stuff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev_vlad.testing_stuff.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.nextBtn.setOnClickListener{
            startActivity(
                Intent(
                    this,
                    DetailsActivity::class.java
                )
            )
        }


    }
}