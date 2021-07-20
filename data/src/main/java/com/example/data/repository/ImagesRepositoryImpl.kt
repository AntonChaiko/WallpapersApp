package com.example.data.repository

import asImagesDb
import asUserDb
import com.example.data.db.ImagesDatabase
import com.example.data.model.Results
import com.example.domain.repository.ImagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImagesRepositoryImpl(database: ImagesDatabase) : ImagesRepository {

    private var imagesDao = database.imagesDao()

    val images = imagesDao.getImages()

    suspend fun insertImage(images: Results) {
        withContext(Dispatchers.IO) {
            imagesDao.insertImage(images.asImagesDb())
        }
    }

    suspend fun insertUser(user: Results) {
        withContext(Dispatchers.IO) {
            imagesDao.insertUser(user.user.asUserDb())
        }
    }

}