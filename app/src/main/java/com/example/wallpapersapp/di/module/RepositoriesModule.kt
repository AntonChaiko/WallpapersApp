package com.example.wallpapersapp.di.module

import com.example.data.db.ImagesDatabase
import com.example.data.remote.api.ImageApiService
import com.example.data.repository.ImageApiRepositoryImpl
import com.example.data.repository.ImagesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideImageApiRepository(
        imageApiService: ImageApiService
    ): ImageApiRepositoryImpl = ImageApiRepositoryImpl(imageApiService)

    @Provides
    @Singleton
    fun provideImagesRepository(
        database: ImagesDatabase
    ): ImagesRepositoryImpl = ImagesRepositoryImpl(database)


}
