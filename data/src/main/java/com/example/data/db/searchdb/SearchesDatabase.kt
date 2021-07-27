package com.example.data.db.searchdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [SearchDb::class], version = 1, exportSchema = false)
abstract class SearchesDatabase : RoomDatabase() {
    abstract fun searchDao(): SearchDao
}