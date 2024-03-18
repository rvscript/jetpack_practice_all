package com.example.jetpackpracticeall.data

class ApiHelperImpl(private val apiService: ApiService): ApiHelper {
    override suspend fun getUsers(): List<UserItem> {
        return apiService.getUsers()
    }
}