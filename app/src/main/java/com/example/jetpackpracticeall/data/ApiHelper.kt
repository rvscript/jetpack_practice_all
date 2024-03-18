package com.example.jetpackpracticeall.data

interface ApiHelper {
    suspend fun getUsers(): List<UserItem>
}