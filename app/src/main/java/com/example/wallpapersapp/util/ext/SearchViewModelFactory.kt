package com.example.wallpapersapp.util.ext

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.model.Results
import com.example.data.repository.ImageApiRepositoryImpl
import com.example.wallpapersapp.ui.screens.imagedetailsfragment.ImageDetailsViewModel
import com.example.wallpapersapp.ui.screens.searchfragment.SearchFragmentViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.lang.IllegalArgumentException

class SearchViewModelFactory @AssistedInject constructor(
    @Assisted("newsId") private val newsId: String,
    private val repository: ImageApiRepositoryImpl,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchFragmentViewModel(newsId, repository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("newsId") newsId: String): SearchViewModelFactory
    }
}

class ImageDetailsViewModelFactory(
    private val currentImage: Results,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageDetailsViewModel::class.java)) {
            return ImageDetailsViewModel(currentImage = currentImage, application = application) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}
