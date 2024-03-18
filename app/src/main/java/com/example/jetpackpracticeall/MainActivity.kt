package com.example.jetpackpracticeall

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.Keep
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var mButton: Button
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mButton = findViewById(R.id.bt_mainActivity)
        mButton.setOnClickListener{
            Toast.makeText(this.applicationContext,"lala", Toast.LENGTH_SHORT).show()
        }
        viewModel.getData()
        viewModel.getDataInt()

    }

}