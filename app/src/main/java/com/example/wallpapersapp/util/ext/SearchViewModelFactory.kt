package com.example.wallpapersapp.util.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wallpapersapp.ui.screens.searchfragment.SearchFragmentViewModel
import javax.inject.Inject
import javax.inject.Provider

class SearchViewModelFactory @Inject constructor(private val myViewModelProvider: Provider<SearchFragmentViewModel>) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return myViewModelProvider.get() as T
    }
}

