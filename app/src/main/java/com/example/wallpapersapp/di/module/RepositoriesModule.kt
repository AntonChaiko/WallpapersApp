package com.example.wallpapersapp.di.module

import com.example.data.db.imagesdb.ImagesDao
import com.example.data.db.searchdb.SearchDao
import com.example.data.remote.api.ImageApiService
import com.example.data.repository.ImageApiRepositoryImpl
import com.example.data.repository.ImagesRepositoryImpl
import com.example.data.repository.SearchRepositoryImpl
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
        imagesDao: ImagesDao
    ): ImagesRepositoryImpl = ImagesRepositoryImpl(imagesDao)

    @Provides
    @Singleton
    fun provideSearchRepository(
        searchDao: SearchDao
    ): SearchRepositoryImpl = SearchRepositoryImpl(searchDao)


}
