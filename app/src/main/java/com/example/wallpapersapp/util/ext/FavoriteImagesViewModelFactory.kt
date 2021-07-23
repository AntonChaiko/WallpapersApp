package com.example.wallpapersapp.util.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.ImagesRepositoryImpl
import com.example.wallpapersapp.ui.screens.favoriteimagesfragment.FavoriteImagesViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


class FavoriteImagesViewModelFactory @AssistedInject constructor(
    @Assisted("text") private val text: String,
    private val imageRepository: ImagesRepositoryImpl,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteImagesViewModel(text, imageRepository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("text") text: String): FavoriteImagesViewModelFactory
    }
}