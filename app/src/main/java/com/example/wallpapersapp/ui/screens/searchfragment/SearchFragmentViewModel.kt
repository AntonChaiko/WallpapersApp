package com.example.wallpapersapp.ui.screens.searchfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.model.Results
import com.example.data.repository.ImageApiRepositoryImpl
import kotlinx.coroutines.flow.Flow

class SearchFragmentViewModel(
    private val newsId: String,
    private val repository: ImageApiRepositoryImpl
) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Results>>? = null

    fun searchRepo(queryString: String): Flow<PagingData<Results>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Results>> = repository.getSearchResultStream(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}

