package com.example.jetpackpracticeall.domian.models.dto

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("Camera")
    val camera: Camera?,
    @SerializedName("earth_date")
    val earth_date: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("img_src")
    val img_src: String?,
    @SerializedName("rover")
    val rover: Rover?,
    @SerializedName("sol")
    val sol: Int?
)