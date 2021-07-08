package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.constants.DEFAULT_PAGE_SIZE
import com.example.data.model.ImageSource
import com.example.data.model.Results
import com.example.data.remote.api.ImageApiService
import com.example.data.remote.api.ImageSearchResponse
import com.example.domain.repository.ImageApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Scope

class ImageApiRepositoryImpl @Inject constructor(
    private val imageApiService: ImageApiService
) : ImageApiRepository {

    fun getSearchResultStream(query: String): Flow<PagingData<Results>> {

        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ImageSource(query, imageApiService)
            }
        ).flow
//            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }


}