package com.example.domain.model.dto

data class SearchEntity(
    val searchQuery:String,
    val resultAmount: Long,
    val searchDate: String,
    var isFavorite: Boolean
) {
}