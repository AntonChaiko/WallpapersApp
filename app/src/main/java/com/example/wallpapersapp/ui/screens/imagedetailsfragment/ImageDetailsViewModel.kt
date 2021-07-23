package com.example.wallpapersapp.ui.screens.imagedetailsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.Results
import com.example.data.repository.ImagesRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageDetailsViewModel @Inject constructor(
    var currentImage: Results,
    private val repository: ImagesRepositoryImpl
) : ViewModel() {


    val imageSize: String
        get() = "Px: ${currentImage.width} x ${currentImage.height}"
    val date: String?
        get() = currentImage.createdAt?.take(10)
    val userName: String
        get() = "@${currentImage.user.userName}"

    fun insertData() {
        viewModelScope.launch {
            repository.insertImage(currentImage)
            repository.insertUser(currentImage)
        }
    }

}