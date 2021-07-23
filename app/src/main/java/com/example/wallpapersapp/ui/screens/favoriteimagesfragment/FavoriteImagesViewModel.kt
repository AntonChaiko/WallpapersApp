package com.example.wallpapersapp.ui.screens.favoriteimagesfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.imagesdb.ImageAndUser
import com.example.data.repository.ImagesRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteImagesViewModel @Inject constructor(
    val text: String,
    private val repository: ImagesRepositoryImpl
) : ViewModel() {

    private val imagesFromDb = repository.images

    fun getImagesLiveData(): LiveData<List<ImageAndUser>> {
        return imagesFromDb
    }

    fun deleteImg(id: Long) {
        viewModelScope.launch {
            repository.deleteImage(id)
            repository.deleteUser(id)
        }
    }

    fun nuke() {
        viewModelScope.launch {
            repository.nukeTable()
        }
    }

}