package com.example.jetpackpracticeall.network

import com.example.jetpackpracticeall.domian.models.dto.ResponseNasaMarsApi
import com.example.jetpackpracticeall.network.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&$API_KEY")
    suspend fun getPhotos(): Response<ResponseNasaMarsApi>
}

object ApiServiceImpl {
    private const val BASE_URL = "https://api.nasa.gov"
    val apiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

class Constants {
    //private val API_KEY = "bBLEcjpDC28Kvv8hU9qgDWUdSAl75KcbyKa5XmnL"
    // https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=bBLEcjpDC28Kvv8hU9qgDWUdSAl75KcbyKa5XmnL
    companion object {
        const val API_KEY = "api_key=bBLEcjpDC28Kvv8hU9qgDWUdSAl75KcbyKa5XmnL"
    }
}