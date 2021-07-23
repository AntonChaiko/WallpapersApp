package com.example.wallpapersapp.util.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.model.Results
import com.example.data.repository.ImagesRepositoryImpl
import com.example.wallpapersapp.ui.screens.imagedetailsfragment.ImageDetailsViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ImageDetailsViewModelFactory @AssistedInject constructor(
    @Assisted("currentImage") private val currentImage: Results,
    private val imageRepository: ImagesRepositoryImpl,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageDetailsViewModel(currentImage, imageRepository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("currentImage") currentImage: Results): ImageDetailsViewModelFactory
    }
}