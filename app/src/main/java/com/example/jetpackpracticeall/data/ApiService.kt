package com.example.jetpackpracticeall.data

import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UserItem>
}