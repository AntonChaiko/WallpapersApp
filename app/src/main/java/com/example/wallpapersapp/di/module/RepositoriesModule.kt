package com.example.wallpapersapp.di.module

import com.example.data.remote.api.ImageApiService
import com.example.data.repository.ImageApiRepositoryImpl
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
}
