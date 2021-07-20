package com.example.wallpapersapp.ui.screens.favoriteimagesfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.data.db.ImageAndUser
import com.example.data.db.getDatabase
import com.example.data.repository.ImagesRepositoryImpl

class FavoriteImagesViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = ImagesRepositoryImpl(getDatabase(application))
    private val imagesFromDb = repository.images

    init {

    }

    fun getImagesLiveData() : LiveData<List<ImageAndUser>> {
        return imagesFromDb
    }

}