package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.data.model.Tags
import com.example.data.model.Urls


@Entity(tableName = "images_db")
data class ImagesDb(
    var imageId: String,
    var urls: Urls,
    var width: Int,
    var height: Int,
    var color: String?,
    var description: String?,
    var createdAt: String?,
    var tags: List<Tags>

) {
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0
}

@Entity
data class UserDb(
    @PrimaryKey(autoGenerate = true)
    var imageOwnerId: Long,
    var name: String,
    var userName: String,
    var twitterUsername: String?,
    var portfolioUrls: String?,
    var profileImage: String?,
    var instagramUsername: String?
)
