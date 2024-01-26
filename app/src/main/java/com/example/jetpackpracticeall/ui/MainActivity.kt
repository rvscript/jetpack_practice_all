package com.example.jetpackpracticeall.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackpracticeall.MainApplication
import com.example.jetpackpracticeall.R
import com.example.jetpackpracticeall.domian.mapper.NasaDtoMapper

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: JetPackViewModel
    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var mapper: NasaDtoMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mapper = NasaDtoMapper()
        viewModelFactory = MainViewModelFactory((application as MainApplication).repository, mapper)
        viewModel = ViewModelProvider(this, viewModelFactory)[JetPackViewModel::class.java]
        if (viewModel.mData.isInitialized) {
            viewModel.mData.observe(this) {
                if (it != null && it.isNotEmpty()) {

                }
            }
        }
    }
}