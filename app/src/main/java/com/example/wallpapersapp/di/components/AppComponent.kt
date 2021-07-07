package com.example.wallpapersapp.di.components

import androidx.lifecycle.ViewModel
import com.example.data.repository.ImageApiRepositoryImpl
import com.example.wallpapersapp.MainActivity
import com.example.wallpapersapp.di.module.ApiModule
import com.example.wallpapersapp.di.module.RepositoriesModule
import com.example.wallpapersapp.ui.screens.testfragment.TestFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiModule::class), (RepositoriesModule::class)])
interface AppComponent {
    fun inject(fragment: TestFragment)
    fun inject(mainActivity: MainActivity)
    val repository: ImageApiRepositoryImpl
}

/*
@Module
object newModule {

    @Provides
    @Singleton
    fun provideComp(proc: Processor): Comp {
        return Comp(proc)
    }

    @Provides
    @Singleton
    fun provideProcessor(): Processor {
        return Processor()
    }
}

data class Comp(val processor: Processor) {

}

class Processor {
    override fun toString(): String = "INTEL?"
}*/
