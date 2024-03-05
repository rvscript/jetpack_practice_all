package com.example.jetpackpracticeall.network

import com.example.jetpackpracticeall.data.TodosItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/todos")
    suspend fun getTodos(): Response<List<TodosItem>>
}