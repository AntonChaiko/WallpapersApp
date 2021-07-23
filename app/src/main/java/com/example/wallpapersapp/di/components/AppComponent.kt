package com.example.wallpapersapp.di.components

import android.content.Context
import com.example.wallpapersapp.MainActivity
import com.example.wallpapersapp.di.module.*
import com.example.wallpapersapp.ui.screens.favoriteimagesfragment.FavoriteImagesFragment
import com.example.wallpapersapp.ui.screens.historyfragment.HistoryFragment
import com.example.wallpapersapp.ui.screens.imagedetailsfragment.ImageDetailsFragment
import com.example.wallpapersapp.ui.screens.searchfragment.SearchFragment
import com.example.wallpapersapp.ui.screens.searchrequestsfragment.SearchRequestsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (ApiModule::class),
        (RepositoriesModule::class),
        (BindModule::class),
        (RoomModule::class)
    ]
)
interface AppComponent {
    fun inject(fragment: SearchFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: ImageDetailsFragment)
    fun inject(fragment: FavoriteImagesFragment)
    fun inject(fragment: SearchRequestsFragment)
    fun inject(fragment: HistoryFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}

