package com.example.jetpackpracticeall.data

import com.example.jetpackpracticeall.data.User

interface ApiHelper {
    suspend fun getUsers(): List<UserItem>
}