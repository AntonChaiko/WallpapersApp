package com.example.data.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ImagesDao {

    @Transaction
    @Query("select * from images_db")
    fun getImages(): LiveData<List<ImageAndUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(image: ImagesDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserDb)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(images: List<ImagesDb>)

    @Query("DELETE FROM images_db")
    fun nukeTable()
}

/*
@Dao
abstract class BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: T)
    @Update
    abstract fun update(entity: T)
    @Delete
    abstract fun delete(entity: T)
}

@Dao
abstract class ReasonDao : BaseDao<ImagesDb?>() {

}*/
