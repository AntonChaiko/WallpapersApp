package com.example.wallpapersapp.ui.screens.searchrequestsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.SearchRepositoryImpl
import com.example.domain.model.dto.SearchEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchRequestsViewModel @Inject constructor(
    private val text: String,
    private val repository: SearchRepositoryImpl
) : ViewModel() {
    val res = repository.searchItems

}