package com.example.jetpackpracticeall.api

import com.example.jetpackpracticeall.models.ResponseMarsPhotos
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    // https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=bBLEcjpDC28Kvv8hU9qgDWUdSAl75KcbyKa5XmnL
    @GET("/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=bBLEcjpDC28Kvv8hU9qgDWUdSAl75KcbyKa5XmnL")
    suspend fun getMarsPhotos(): Response<ResponseMarsPhotos>
}