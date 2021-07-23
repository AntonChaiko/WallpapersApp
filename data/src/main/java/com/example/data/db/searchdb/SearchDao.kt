package com.example.data.db.searchdb

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface SearchDao {
    @Transaction
    @Query("select * from search_db")
    fun getSearches(): LiveData<List<SearchDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearch(image: SearchDb)
}