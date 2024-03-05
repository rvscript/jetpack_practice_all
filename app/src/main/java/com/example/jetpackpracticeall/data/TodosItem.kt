package com.example.jetpackpracticeall.data

data class TodosItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)