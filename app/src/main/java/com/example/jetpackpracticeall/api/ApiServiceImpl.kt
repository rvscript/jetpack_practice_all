package com.example.jetpackpracticeall.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceImpl {
    // https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=bBLEcjpDC28Kvv8hU9qgDWUdSAl75KcbyKa5XmnL
    private val BASE_URL = "https://api.nasa.gov"

    val apiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}