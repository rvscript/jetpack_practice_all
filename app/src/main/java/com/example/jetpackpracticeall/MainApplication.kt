package com.example.jetpackpracticeall

import android.app.Application
import com.example.jetpackpracticeall.db.PhotosDatabase
import com.example.jetpackpracticeall.network.ApiServiceImpl
import com.example.jetpackpracticeall.repository.NasaRepositoryImpl

class MainApplication: Application() {
    val photosDatabase by lazy {
        PhotosDatabase.getDatabase(this)
    }

    val repository by lazy {
        val apiService = ApiServiceImpl
        NasaRepositoryImpl(this, apiService)
    }
}