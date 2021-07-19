package com.example.wallpapersapp.ui.screens.imagedetailsfragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.data.db.ImageAndUser
import com.example.data.db.ImagesDatabase
import com.example.data.db.getDatabase
import com.example.data.model.Results
import com.example.data.repository.ImagesRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageDetailsViewModel(
    application: Application,
    var currentImage: Results
) : AndroidViewModel(application) {

    private val repository = ImagesRepositoryImpl(getDatabase(application))
    val x = repository.res
    init {

    }

    val imageSize: String
        get() = "Px: ${currentImage.width} x ${currentImage.height}"
    val date: String?
        get() = currentImage.createdAt?.take(10)
    val userName: String
        get() = "@${currentImage.user.userName}"

    /* fun getSomeData() {

         viewModelScope.launch {
             list = repository.getAllImages()
             Log.d("asd","worked")
             Log.d("asd",list.size.toString())
         }
     }*/
    fun insertData(currentImage: Results) {
        viewModelScope.launch {
            repository.insertImage(currentImage)
            repository.insertUser(currentImage)
        }
    }

    /* fun getData25() {
         viewModelScope.launch {
             list = repository.getAllImages()
         }
     }*/
}