package com.example.jetpackpracticeall.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackpracticeall.ui.viewModels.MainViewModel
import com.example.jetpackpracticeall.R
import com.example.jetpackpracticeall.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container_fragment, Fragment1())
            .commit()
    }
}