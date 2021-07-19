package com.example.wallpapersapp.ui.screens.imagedetailsfragment

import androidx.lifecycle.ViewModel
import com.example.data.model.Results

class ImageDetailsViewModel(
    var currentImage: Results
) : ViewModel() {

    val imageSize: String
        get() = "Px: ${currentImage.width} x ${currentImage.height}"
    val date: String?
        get() = currentImage.createdAt?.take(10)
    val userName: String
        get() = "@${currentImage.user.userName}"

//    private fun String.convertToMonth(): String = months[this.toInt() - 1]


}