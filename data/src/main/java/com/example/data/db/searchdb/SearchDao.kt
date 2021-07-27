package com.example.data.db.searchdb

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface SearchDao {
    @Transaction
    @Query("select * from search_db")
    fun getSearches(): LiveData<List<SearchDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearch(searchDb: SearchDb)

    @Update
    fun updateSearch(searchDb: SearchDb)

    @Query("select * from search_db where isFavorite=1")

    fun getAllFavoritesSearches(): LiveData<List<SearchDb>>

    @Query("select * from search_db where searchQuery LIKE :query")
    fun searchByQuery(query: String): SearchDb


    @Delete
    fun deleteSearch(searchDb: SearchDb)
}