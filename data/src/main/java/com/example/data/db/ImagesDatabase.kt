package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [ImagesDb::class, UserDb::class], version = 2, exportSchema = false)
@TypeConverters(Convertors::class)
abstract class ImagesDatabase : RoomDatabase() {
    abstract fun imagesDao(): ImagesDao
}

private lateinit var INSTANCE: ImagesDatabase


fun getDatabase(context: Context): ImagesDatabase {
    synchronized(ImagesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ImagesDatabase::class.java,
                "images_db"
            ).fallbackToDestructiveMigration().build()
        }
    }
    return INSTANCE
}