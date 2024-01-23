package com.example.jetpackpracticeall.repository

import com.example.jetpackpracticeall.api.ApiServiceImpl
import com.example.jetpackpracticeall.models.ResponseMarsPhotos
import retrofit2.Response

interface NasaRepository {
    suspend fun getNasaMarsRoverPhotos(): Response<ResponseMarsPhotos>
}

class NasaRepositoryImpl(
    private val apiService: ApiServiceImpl
): NasaRepository {
    override suspend fun getNasaMarsRoverPhotos(): Response<ResponseMarsPhotos> {
        val api = apiService.apiService
        return api.getMarsPhotos()
    }
}