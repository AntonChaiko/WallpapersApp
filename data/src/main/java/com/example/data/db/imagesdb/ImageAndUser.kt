package com.example.data.db.imagesdb

import androidx.room.Embedded
import androidx.room.Relation

data class ImageAndUser(
    @Embedded val imagesDb: ImagesDb,
    @Relation(
        parentColumn = "userId",
        entityColumn = "imageOwnerId"
    )
    val userDb: UserDb?
)