package com.example.wallpapersapp

import android.app.Application
import android.content.Context
import com.example.wallpapersapp.di.components.AppComponent
import com.example.wallpapersapp.di.components.DaggerAppComponent

class MainApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }