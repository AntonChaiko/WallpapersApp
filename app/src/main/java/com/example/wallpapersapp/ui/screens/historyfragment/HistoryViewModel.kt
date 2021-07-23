package com.example.wallpapersapp.ui.screens.historyfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.searchdb.SearchDb
import com.example.data.repository.SearchRepositoryImpl
import com.example.domain.model.dto.SearchEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryViewModel @Inject constructor(

    private val searchEntity: SearchEntity,
    private val repository: SearchRepositoryImpl

) : ViewModel() {
    val res = repository.searchItems

    fun updateSearchDatabase(searchDb: SearchDb) {
        viewModelScope.launch {
            searchDb.isFavorite = !searchDb.isFavorite
            repository.updateSearch(searchDb)
        }
    }

}