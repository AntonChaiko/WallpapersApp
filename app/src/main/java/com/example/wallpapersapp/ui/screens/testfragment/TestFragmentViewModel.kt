package com.example.wallpapersapp.ui.screens.testfragment

import android.util.Log
import androidx.lifecycle.*
import com.example.data.model.BaseEntity
import com.example.data.model.ImageEntity
import com.example.data.model.Results
import com.example.data.remote.api.ImageApi
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class TestFragmentViewModel : ViewModel() {

    private val _photos = MutableLiveData<List<ImageEntity>>()
    val photos: LiveData<List<ImageEntity>> = _photos

    private val _collection = MutableLiveData<BaseEntity>()
    val collection: LiveData<BaseEntity> = _collection

    init {
        Log.d("asd","view model init")
        getPhotos()
        Log.d("asd","${photos.value?.get(0)?.id}")
    }

    private fun getPhotos() {
        viewModelScope.launch {
            try {
                _photos.value = ImageApi.retrofitService.getImages()
                _collection.value = ImageApi.retrofitService.getCollection("dog")
                Log.d("asd","success")
                Log.d("asd","${photos.value?.get(0)?.links?.full}")
                Log.d("asd","dogs ${collection.value?.results?.get(0)?.cover_photo}")

            } catch (e: Exception) {
                Log.d("asd",e.message.toString())
            }
        }
    }
    fun refreshPhotos() : LiveData<List<ImageEntity>>{
        return photos
    }
}