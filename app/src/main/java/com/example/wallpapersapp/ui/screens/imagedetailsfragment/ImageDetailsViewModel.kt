package com.example.wallpapersapp.ui.screens.imagedetailsfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.data.db.getDatabase
import com.example.data.model.Results
import com.example.data.repository.ImagesRepositoryImpl
import javax.inject.Inject

class ImageDetailsViewModel(
    application: Application,
    var currentImage: Results
) : AndroidViewModel(application) {

    val repository = ImagesRepositoryImpl(getDatabase(application))

    val list: LiveData<List<Results>> = repository.images


    val imageSize: String
        get() = "Px: ${currentImage.width} x ${currentImage.height}"
    val date: String?
        get() = currentImage.createdAt?.take(10)
    val userName: String
        get() = "@${currentImage.user.userName}"

}