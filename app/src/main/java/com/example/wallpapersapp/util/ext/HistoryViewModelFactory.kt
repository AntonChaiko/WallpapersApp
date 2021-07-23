package com.example.wallpapersapp.util.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.SearchRepositoryImpl
import com.example.domain.model.dto.SearchEntity
import com.example.wallpapersapp.ui.screens.historyfragment.HistoryViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


class HistoryViewModelFactory @AssistedInject constructor(
    @Assisted("searches") private val searches: SearchEntity,
    private val searchRepository: SearchRepositoryImpl,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HistoryViewModel(searches, searchRepository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("searches") searches: SearchEntity): HistoryViewModelFactory
    }
}