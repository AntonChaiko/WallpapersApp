package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "images_db")
data class ImagesDb(
    var imageId: String,
    @PrimaryKey(autoGenerate = true)
    var userId: Long,
    var urls: String,
    var width: Int,
    var height: Int,
    var color: String?,
    var description: String?,
    var createdAt: String?,
    var tags: String? = null

)

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
