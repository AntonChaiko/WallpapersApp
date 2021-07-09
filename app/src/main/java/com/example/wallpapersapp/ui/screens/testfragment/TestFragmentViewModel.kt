package com.example.wallpapersapp.ui.screens.testfragment

import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
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
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.selects.whileSelect
import java.util.concurrent.TimeUnit

class TestFragmentViewModel(
    private val newsId: String,
    private val repository: ImageApiRepositoryImpl
) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Results>>? = null


    init {

    }

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

    fun EditText.onTextChanged(): ReceiveChannel<String> =
        Channel<String>(capacity = Channel.UNLIMITED).also {
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(editable: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

            })
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