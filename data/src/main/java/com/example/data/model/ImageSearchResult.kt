package com.example.data.model

import java.lang.Exception

sealed class ImageSearchResult {
    data class Success(val data: List<Results>) : ImageSearchResult()
    data class Error(val error: Exception) : ImageSearchResult()
}