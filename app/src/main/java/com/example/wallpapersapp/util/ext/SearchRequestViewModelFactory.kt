package com.example.wallpapersapp.util.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.model.Results
import com.example.data.repository.ImagesRepositoryImpl
import com.example.data.repository.SearchRepositoryImpl
import com.example.wallpapersapp.ui.screens.imagedetailsfragment.ImageDetailsViewModel
import com.example.wallpapersapp.ui.screens.searchrequestsfragment.SearchRequestsViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


class SearchRequestViewModelFactory @AssistedInject constructor(
    @Assisted("text") private val text: String,
    private val searchRepository: SearchRepositoryImpl,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchRequestsViewModel(text, searchRepository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("text") text: String): SearchRequestViewModelFactory
    }
}