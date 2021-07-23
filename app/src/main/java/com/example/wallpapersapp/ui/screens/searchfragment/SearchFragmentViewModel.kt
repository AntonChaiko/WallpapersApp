package com.example.wallpapersapp.ui.screens.searchfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.model.Results
import com.example.data.model.SearchEntity
import com.example.data.repository.ImageApiRepositoryImpl
import com.example.data.repository.SearchRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(
     val repository: ImageApiRepositoryImpl,
    private val searchRepository: SearchRepositoryImpl
) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Results>>? = null
    var total: Long = 0

    var text = "london"

    fun searchRepo(queryString: String): Flow<PagingData<Results>> {
        val lastResult = currentSearchResult

        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString

        val newResult: Flow<PagingData<Results>> = repository.getSearchResultStream(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult

        viewModelScope.launch {
            total = repository.getTotal(queryString)
        }

        return newResult
    }

    fun addSearchQuery(searchEntity: com.example.domain.model.dto.SearchEntity) {
        viewModelScope.launch {
            searchRepository.insertSearch(searchEntity)
        }

    }

    fun createEntity(
        searchQuery: String,
        resultAmount: Long,
        searchDate: String,
        isFavorite: Boolean
    ): com.example.domain.model.dto.SearchEntity {
        return com.example.domain.model.dto.SearchEntity(
            searchQuery,
            resultAmount,
            searchDate,
            isFavorite
        )
    }

}

