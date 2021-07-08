package com.example.wallpapersapp.ui.screens.testfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.model.Results
import com.example.data.repository.ImageApiRepositoryImpl
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow

class TestFragmentViewModel(
    private val newsId: String,
    private val repository: ImageApiRepositoryImpl
) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Results>>? = null


    init {
    }

    fun searchRepo(queryString:String) : Flow<PagingData<Results>> {

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

class NewsDetailsViewModelFactory @AssistedInject constructor(
    @Assisted("newsId") private val newsId: String,
    private val repository: ImageApiRepositoryImpl,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TestFragmentViewModel(newsId, repository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("newsId") newsId: String): NewsDetailsViewModelFactory
    }
}