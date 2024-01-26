package com.example.jetpackpracticeall.repository

import android.app.Application
import android.util.Log
import com.example.jetpackpracticeall.db.PhotoDao
import com.example.jetpackpracticeall.db.PhotosDatabase
import com.example.jetpackpracticeall.db.models.domains.PhotoDomain
import com.example.jetpackpracticeall.domian.models.dto.ResponseNasaMarsApi
import com.example.jetpackpracticeall.network.ApiServiceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response

interface NasaRepository {
    suspend fun getNasaMarsRoverPhotos(): Response<ResponseNasaMarsApi>
    suspend fun insert(photoDomain: PhotoDomain)
    suspend fun getAndPostAllPhotos(): Flow<List<PhotoDomain>>
    suspend fun update(photoDomain: PhotoDomain)
    suspend fun delete(photoDomain: PhotoDomain)
    suspend fun deleteAllPhotos()
}

class NasaRepositoryImpl(
    application: Application,
    private val apiService: ApiServiceImpl,
): NasaRepository {
    // database must be created first to then create dao and call queries
    val database = PhotosDatabase.getDatabase(application)
    val photoDao: PhotoDao = database.photoDao()

    override suspend fun getNasaMarsRoverPhotos(): Response<ResponseNasaMarsApi> {
        val api = apiService.apiService
        return api.getPhotos()
    }

    override suspend fun getAndPostAllPhotos(): Flow<List<PhotoDomain>> {
        return photoDao.getAllPhotos()
    }
    override suspend fun insert(photoDomain: PhotoDomain) {
        CoroutineScope(IO).launch {
            try {
                photoDao.insert(photoDomain)
            } catch (e: Exception) {
                Log.d("JetPack", "getMarsPhotos:Error: ${e.message}, ${e.stackTrace} ")
            }
        }
    }
    override suspend fun update(photoDomain: PhotoDomain) {
        CoroutineScope(IO).launch {
            try {
                photoDao.insert(photoDomain)
            } catch (e: Exception) {
                Log.d("JetPack", "getMarsPhotos:Error: ${e.message}, ${e.stackTrace} ")
            }
        }
    }
    override suspend fun delete(photoDomain: PhotoDomain) {
        CoroutineScope(IO).launch {
            try {
                photoDao.delete(photoDomain)
            } catch (e: Exception) {
                Log.d("JetPack", "getMarsPhotos:Error: ${e.message}, ${e.stackTrace} ")
            }
        }
    }
    override suspend fun deleteAllPhotos() {
        CoroutineScope(IO).launch {
            try {
                photoDao.deleteAllPhots()
            } catch (e: Exception) {
                Log.d("JetPack", "getMarsPhotos:Error: ${e.message}, ${e.stackTrace} ")
            }
        }
    }
}