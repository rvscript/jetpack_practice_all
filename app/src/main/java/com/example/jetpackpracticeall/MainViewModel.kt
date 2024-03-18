package com.example.jetpackpracticeall

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val list = MutableLiveData<String>()

    fun getData(): String{
        return "Mocked data"
    }

    fun getDataInt(): Int {
        return 5
    }
}