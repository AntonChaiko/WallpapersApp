package com.example.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class ImageAndUser(
    @Embedded val imagesDb: ImagesDb,
    @Relation(
        parentColumn = "userId",
        entityColumn = "imageOwnerId"
    )
    val userDb: UserDb?
) {
}