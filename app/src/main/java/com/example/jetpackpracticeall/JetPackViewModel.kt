package com.example.jetpackpracticeall

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.jetpackpracticeall.models.ResponseMarsPhotos
import com.example.jetpackpracticeall.repository.NasaRepository
import kotlinx.coroutines.launch

class JetPackViewModel(
    val repository: NasaRepository
): ViewModel() {
    val _responseLiveData = MutableLiveData<ResponseMarsPhotos>()
    val responseLiveData: LiveData<ResponseMarsPhotos>
        get() = _responseLiveData

    private fun getMarsPhotos() = viewModelScope.launch {
        repository.getNasaMarsRoverPhotos().let { response ->
            if (response.isSuccessful) {
                _responseLiveData.postValue(response.body())
                response.body()?.photos?.forEach{
                    Log.d("JetPack", "IMAGE TITLE = ${it.id} IMAGE SOURCE = ${it.img_src}")
                }
            } else {
                Log.e("JetPack", "getMarsPhotos: ${response.message()}", )
            }
        }
    }

    init {
        getMarsPhotos()
    }
}

class MainViewModelFactory(private val repository: NasaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JetPackViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JetPackViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}