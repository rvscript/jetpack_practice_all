package com.example.jetpackpracticeall.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetpackpracticeall.db.models.domains.PhotoDomain
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(photoDomain: PhotoDomain)
    @Update
    fun update(photoDomain: PhotoDomain)
    @Delete
    fun delete(photoDomain: PhotoDomain)
    @Query("DELETE FROM photo_domain_table")
    fun deleteAllPhots()
    @Query("SELECT * FROM photo_domain_table ORDER BY id")
    fun getAllPhotos(): Flow<List<PhotoDomain>>
}