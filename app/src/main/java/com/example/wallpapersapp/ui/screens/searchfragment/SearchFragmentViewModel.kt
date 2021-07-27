package com.example.wallpapersapp.ui.screens.searchfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.db.searchdb.SearchDb
import com.example.data.model.Results
import com.example.data.repository.ImageApiRepositoryImpl
import com.example.data.repository.SearchRepositoryImpl
import com.example.domain.model.dto.SearchEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(
    val repository: ImageApiRepositoryImpl,
    private val searchRepository: SearchRepositoryImpl
) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Results>>? = null

    var total: Long = 0
    var text = "London"

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

    private fun addSearchQuery(searchEntity: SearchEntity) {
        viewModelScope.launch {
            searchRepository.insertSearch(searchEntity)
        }
    }
    suspend fun writeSearchFieldIntoDatabase(query:String) {
        searchByQuery(query)
        viewModelScope.launch {
            total = repository.getTotal(query)
        }
        delay(1000)
        val isFavorite: Boolean? = x?.isFavorite
        Log.d("asd", isFavorite.toString())
        addSearchQuery(
            SearchEntity(
                text,
                total,
                DateTime.now().toString().take(19),
                isFavorite == true
            )
        )
    }

     var x: SearchDb? = null
    private fun searchByQuery(query: String) {
        viewModelScope.launch {
            x = searchRepository.searchByQuery(query)
        }
    }
}

