package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.data.db.ImagesDatabase
import com.example.data.mapper.asEntityModel
import com.example.data.mapper.asImagesDb
import com.example.data.mapper.asResultModel
import com.example.data.mapper.asUserDb
import com.example.data.model.Results
import com.example.domain.repository.ImagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImagesRepositoryImpl(database: ImagesDatabase) : ImagesRepository {

    private var imagesDao = database.imagesDao()
    val images: LiveData<List<Results>> = Transformations.map(imagesDao.getImages()) {
        it.asResultModel()
    }


    suspend fun insertImage(images: Results) {
        withContext(Dispatchers.IO) {
            imagesDao.insertImage(image = images.asImagesDb())
        }
    }

    suspend fun insertUser(user: Results) {
        withContext(Dispatchers.IO) {
            imagesDao.insertUser(user.user.asUserDb())
        }
    }

}