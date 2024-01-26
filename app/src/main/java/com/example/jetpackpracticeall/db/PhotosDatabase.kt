package com.example.jetpackpracticeall.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpackpracticeall.db.models.domains.PhotoDomain

@Database(entities = [
    PhotoDomain::class
                     ],
    version = 1
)
abstract class PhotosDatabase: RoomDatabase() {
    abstract fun photoDao(): PhotoDao
    companion object {
        // singleton instance of the database
        @Volatile
        private var INSTANCE: PhotosDatabase? = null
        fun getDatabase(context: Context): PhotosDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhotosDatabase::class.java,
                    "photos_database"
                )
                    .fallbackToDestructiveMigration()
//                    .addCallback(RDB())
                    .build()
                INSTANCE = instance
                instance
            }
        }


//        class RDB() : RoomDatabase.Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//            }
//
//            override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
//                super.onDestructiveMigration(db)
//            }
//
//            override fun onOpen(db: SupportSQLiteDatabase) {
//                super.onOpen(db)
//            }
//
//        }
    }
}