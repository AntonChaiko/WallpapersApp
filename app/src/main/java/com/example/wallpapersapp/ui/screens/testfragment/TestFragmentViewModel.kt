package com.example.wallpapersapp.ui.screens.testfragment

import android.util.Log
import androidx.lifecycle.*
import com.example.data.model.BaseEntity
import com.example.data.model.ImageEntity
import com.example.data.repository.ImageApiRepositoryImpl
import com.example.domain.repository.ImageApiRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import javax.inject.Inject

class TestFragmentViewModel(
    private val newsId: String,
    private val repository: ImageApiRepositoryImpl
    ) : ViewModel() {

    private val _photos = MutableLiveData<List<ImageEntity>>()
    val photos: LiveData<List<ImageEntity>> = _photos

    private val _collection = MutableLiveData<BaseEntity>()
    val collection: LiveData<BaseEntity> = _collection

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            try {
                _photos.value = repository.getListOfImages()

            } catch (e: Exception) {
                Log.d("asd", e.message.toString())
            }
        }
    }

}
class NewsDetailsViewModelFactory @AssistedInject constructor(
    @Assisted("newsId") private val newsId: String,
    private val repository: ImageApiRepositoryImpl,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TestFragmentViewModel(newsId,repository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("newsId") newsId: String): NewsDetailsViewModelFactory
    }
}