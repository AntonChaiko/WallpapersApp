package com.example.data.db.imagesdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ImagesDb::class, UserDb::class], version = 2, exportSchema = false)
@TypeConverters(Convertors::class)
abstract class ImagesDatabase : RoomDatabase() {
    abstract fun imagesDao(): ImagesDao
}
