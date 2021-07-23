package com.example.data.repository

import asSearchDb
import com.example.data.db.searchdb.SearchDao
import com.example.domain.model.dto.SearchEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDao: SearchDao
) {

    val searchItems = searchDao.getSearches()

    suspend fun insertSearch(image: SearchEntity) {
        withContext(Dispatchers.IO) {
            searchDao.insertSearch(image.asSearchDb())
        }
    }
}