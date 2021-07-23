package com.example.data.db.searchdb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "search_db")
data class SearchDb(
    val searchQuery:String,
    val resultAmount: Long,
    val searchDate: String,
    val isFavorite: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}