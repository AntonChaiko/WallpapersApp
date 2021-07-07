package com.example.data.repository

import com.example.data.model.ImageEntity
import com.example.data.remote.api.ImageApiService
import com.example.domain.repository.ImageApiRepository
import javax.inject.Inject

class ImageApiRepositoryImpl @Inject constructor(private val imageApiService: ImageApiService) :
    ImageApiRepository {

    suspend fun getListOfImages(): List<ImageEntity> {
        return imageApiService.getImages()
    }
}