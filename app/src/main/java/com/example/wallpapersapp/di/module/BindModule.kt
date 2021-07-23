package com.example.wallpapersapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.wallpapersapp.util.ext.FavoriteImagesViewModelFactory
import com.example.wallpapersapp.util.ext.SearchViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class BindModule {
    @Binds
    @Singleton
    abstract fun bindsViewModelFactory(factory: SearchViewModelFactory): ViewModelProvider.Factory

//    @Binds
//    @Singleton
//    abstract fun bindsFavoritesViewModelFactory(factory: FavoriteImagesViewModelFactory): ViewModelProvider.Factory

}