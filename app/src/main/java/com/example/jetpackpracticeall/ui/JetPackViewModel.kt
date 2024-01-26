package com.example.jetpackpracticeall.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.jetpackpracticeall.db.models.domains.PhotoDomain
import com.example.jetpackpracticeall.domian.mapper.NasaDtoMapper
import com.example.jetpackpracticeall.repository.NasaRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JetPackViewModel(
    val repository: NasaRepository,
    val mapper: NasaDtoMapper
): ViewModel() {
    val _mData = MutableLiveData<List<PhotoDomain>>()
    val mData: LiveData<List<PhotoDomain>>
        get() = _mData
    private fun getMarsPhotos() = viewModelScope.launch {
        repository.getNasaMarsRoverPhotos().let { response ->
            if (response.isSuccessful) {
                val list = response.body()?.photos
                val photoDomains = ArrayList<PhotoDomain>()
                list?.let { l ->
                    photoDomains.addAll(mapper.toDomainList(l))
                    photoDomains.forEach{photoDomain ->
                        try {
                            insertPhotoDomain(photoDomain)
                        } catch (e: Exception) {
                            Log.d("JetPack", "getMarsPhotos:Error: ${e.message}, ${e.stackTrace} ")
                        }
                    }
                    getAllPhotosFromDb()
                }
            } else {
                Log.e("JetPack", "getMarsPhotos: ${response.message()}")
            }
        }
    }

    private fun getAllPhotosFromDb() {
        viewModelScope.launch {
            withContext(IO) {
                val result = repository.getAndPostAllPhotos().asLiveData()
                _mData.postValue(result.value)
            }
        }
    }

    fun insertPhotoDomain(photoDomain: PhotoDomain) {
        viewModelScope.launch {
            repository.insert(photoDomain)
        }
    }

    init {
        getMarsPhotos()
    }
}

class MainViewModelFactory(private val repository: NasaRepository, mapper: NasaDtoMapper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JetPackViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JetPackViewModel(repository, mapper = NasaDtoMapper()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}