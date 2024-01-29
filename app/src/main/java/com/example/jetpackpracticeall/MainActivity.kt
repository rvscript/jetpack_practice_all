package com.example.jetpackpracticeall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.Keep

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

data class someData(
    val str: String
)