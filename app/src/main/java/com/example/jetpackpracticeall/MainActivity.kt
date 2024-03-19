package com.example.jetpackpracticeall

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var mButton: Button
    lateinit var mTextView: TextView
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mButton = findViewById(R.id.bt_mainActivity)
        mTextView = findViewById(R.id.hello_textview)
        mButton.setOnClickListener{
            Toast.makeText(this.applicationContext,"lala", Toast.LENGTH_SHORT).show()
            mTextView.text = "lala"
        }
        viewModel.getData()
        viewModel.getDataInt()

    }

}