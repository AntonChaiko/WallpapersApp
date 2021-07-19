package com.example.data.mapper

import android.media.Image
import com.example.data.db.ImageAndUser
import com.example.data.db.ImagesDb
import com.example.data.db.UserDb
import com.example.data.model.*

fun List<ImageAndUser>.asResultModel(): List<Results> {
    return map {
        Results(
            id = it.imagesDb.imageId,
            urls = it.imagesDb.urls.asUrlModel(),
            user = it.userDb.asResultModel(),
            width = it.imagesDb.width,
            height = it.imagesDb.height,
            color = it.imagesDb.color,
            description = it.imagesDb.description,
            createdAt = it.imagesDb.createdAt,
            tags = null

        )
    }
}

fun Results.asEntityModel(): ImageAndUser {
    return ImageAndUser(
        imagesDb = this.asImagesDb(),
        userDb = this.user.asUserDb()
    )
}

fun Results.asImagesDb(): ImagesDb {
    return ImagesDb(
        imageId = this.id,
        userId = 0,
        urls = this.urls.regular,
        width = this.width,
        height = this.height,
        color = this.color,
        description = this.description,
        createdAt = this.createdAt,
        tags = null
    )
}

fun User.asUserDb(): UserDb {
    return UserDb(
        imageOwnerId = 0,
        name = this.name,
        userName = this.userName,
        twitterUsername = this.twitterUsername,
        portfolioUrls = this.portfolioUrl,
        profileImage = this.profileImage.medium,
        instagramUsername = this.instagramUsername
    )
}

fun UserDb.asResultModel(): User {
    return User(
        userName = this.userName,
        name = this.name,
        twitterUsername = this.twitterUsername,
        portfolioUrl = this.portfolioUrls,
        profileImage = this.profileImage?.asResultModel()!!,
        instagramUsername = this.instagramUsername
    )
}

fun String.asResultModel(): ProfileImage {
    return ProfileImage("", this)
}

fun String.asUrlModel(): Urls {
    return Urls("", "", this, "")
}

//@JvmName("asEmployeeModelImagesDb")
//fun List<ImagesDb>.asEmployeeModel(): List<Results> {
//    return map {
//        Results(
//            id = 0,
//            urls = it.urls,
//            user = it.user,
//            width = it.width,
//            height = it.height,
//            color = it.color,
//            description = it.description,
//            createdAt = it.createdAt,
//            tags = null
//        )
//    }
//}
//fun UserDb.asUserSingleItem(): UserDb {
//    return UserDb(
//        id = 0,
//        userName = this.userName,
//        twitterUsername = this.twitterUsername,
//        portfolioUrls = this.portfolioUrls,
//        profileImage = this.profileImage,
//        instagramUsername = this.instagramUsername
//    )
//}

//fun Results.asEmployeeSingleItem(): ImagesDb {
//    return ImagesDb(
//        imageId = this.id,
//        userId = 0,
//        urls = this.urls,
//        user = this.user,
//        width = this.width,
//        height = this.height,
//        color = this.color,
//        description = this.description,
//        createdAt = this.createdAt,
//        tags = null
//    )
//
//}
