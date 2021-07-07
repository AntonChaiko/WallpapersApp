package com.example.wallpapersapp.util.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.repository.ImageApiRepository

class TestViewModelFactory(private val repository: ImageApiRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TestViewModelFactory(repository) as T
    }
}