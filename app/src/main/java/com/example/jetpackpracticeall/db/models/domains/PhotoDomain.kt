package com.example.jetpackpracticeall.db.models.domains

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("photo_domain_table")
data class PhotoDomain(
    @ColumnInfo(name = "cameraId")
    val camera: Int?,
    val earth_date: String?,
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val img_src: String?,
    @ColumnInfo(name = "roverId")
    val rover: Int?,
    val sol: Int?
)