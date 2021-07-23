package com.example.data.repository

import asImagesDb
import asUserDb
import com.example.data.db.imagesdb.ImagesDao
import com.example.data.db.imagesdb.ImagesDatabase
import com.example.data.model.Results
import com.example.domain.repository.ImagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImagesRepositoryImpl @Inject constructor(
    private val imageDao: ImagesDao
    ) : ImagesRepository {


    val images = imageDao.getImages()

    suspend fun insertImage(images: Results) {
        withContext(Dispatchers.IO) {
            imageDao.insertImage(images.asImagesDb())
        }
    }

    suspend fun insertUser(user: Results) {
        withContext(Dispatchers.IO) {
            imageDao.insertUser(user.user.asUserDb())
        }
    }

    suspend fun deleteUser(id: Long) {
        withContext(Dispatchers.IO) {
            imageDao.deleteByUserId(id)
        }
    }

    suspend fun deleteImage(id: Long) {
        withContext(Dispatchers.IO) {
            imageDao.deleteByImageId(id)
        }
    }

    suspend fun nukeTable() {
        withContext(Dispatchers.IO) {
            imageDao.nukeTable()
            imageDao.nukeTable1()
        }
    }
}