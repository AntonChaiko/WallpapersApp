package com.example.data.db.searchdb

data class SearchEntity(
    val resultAmount:Int,
    var queryString:String,
    val searchDate:String,
    val isFavorite:Boolean
) {
}