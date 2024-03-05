package com.example.jetpackpracticeall.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackpracticeall.network.ApiService
import com.example.jetpackpracticeall.data.TodosItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainViewModel: ViewModel() {

    private val _data = MutableLiveData<List<TodosItem>>()
    val data: LiveData<List<TodosItem>>
        get() = _data

    val apiService = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    private fun getTodoItems() {
        CoroutineScope(IO).launch {
            try {
                apiService.getTodos().let { res ->
                    if (res.isSuccessful) {
                        _data.postValue(res.body())
                    } else {
                        Log.e("API", "getTodoItems: ERROR, headers: ${res.headers()}, \nmessage ${res.errorBody()}", )
                    }
                }
            } catch (e: Exception) {
                Log.e("API", "getTodoItems: EXCEPTION ${e.message}, \n stacktrace ${e.stackTrace}", )
            }
        }
    }
    init {
        getTodoItems()
    }
}