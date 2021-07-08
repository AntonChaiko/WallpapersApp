package com.example.wallpapersapp.di.components

import com.example.wallpapersapp.MainActivity
import com.example.wallpapersapp.di.module.ApiModule
import com.example.wallpapersapp.di.module.RepositoriesModule
import com.example.wallpapersapp.ui.screens.testfragment.TestFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiModule::class), (RepositoriesModule::class)])
interface AppComponent {
    fun inject(fragment: TestFragment)
    fun inject(mainActivity: MainActivity)
}

