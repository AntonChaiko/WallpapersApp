package com.example.wallpapersapp.di.module

import android.content.Context
import androidx.room.Room
import com.example.data.db.imagesdb.ImagesDatabase
import com.example.data.db.searchdb.SearchesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): ImagesDatabase =
        Room.databaseBuilder(context, ImagesDatabase::class.java, "images_db").build()

    @Provides
    @Singleton
    fun provideDao(db: ImagesDatabase) = db.imagesDao()

    @Provides
    @Singleton
    fun provideSearchDatabase(context: Context) : SearchesDatabase =
        Room.databaseBuilder(context, SearchesDatabase::class.java,"search_db").build()

    @Provides
    @Singleton
    fun provideSearchDao(db: SearchesDatabase) = db.searchDao()
}

