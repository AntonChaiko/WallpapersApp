package com.example.data.model

data class SearchEntity(
    val resultAmount:Int,
    var queryString:String,
    val searchDate:String,
    val isFavorite:Boolean
) {
}