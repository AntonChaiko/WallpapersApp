import com.example.data.db.imagesdb.ImageAndUser
import com.example.data.db.imagesdb.ImagesDb
import com.example.data.db.imagesdb.UserDb
import com.example.data.db.searchdb.SearchDb
import com.example.data.model.ProfileImage
import com.example.data.model.Results
import com.example.data.model.Urls
import com.example.data.model.User
import com.example.domain.model.dto.SearchEntity

//package com.example.data.mapper
//
//import android.media.Image
//import com.example.data.db.imagesdb.ImageAndUser
//import com.example.data.db.imagesdb.ImagesDb
//import com.example.data.db.imagesdb.UserDb
//import com.example.data.model.*
//
//fun List<ImageAndUser>.asResultModel(): List<Results> {
//    return map {
//        Results(
//            id = it.imagesDb.imageId,
//            urls = it.imagesDb.urls.asUrlModel(),
//            user = it.userDb.asResultModel(),
//            width = it.imagesDb.width,
//            height = it.imagesDb.height,
//            color = it.imagesDb.color,
//            description = it.imagesDb.description,
//            createdAt = it.imagesDb.createdAt,
//            tags = null
//
//        )
//    }
//}
//
//fun Results.asEntityModel(): ImageAndUser {
//    return ImageAndUser(
//        imagesDb = this.asImagesDb(),
//        userDb = this.user.asUserDb()
//    )
//}
//
fun Results.asImagesDb(): ImagesDb {
    return ImagesDb(
        imageId = this.id,
        urls = this.urls,
        width = this.width,
        height = this.height,
        color = this.color,
        description = this.description,
        createdAt = this.createdAt,
        tags = this.tags
    )
}

fun ImageAndUser.asResultModel(): Results {
    return Results(
        id = this.imagesDb.imageId,
        urls = Urls("", "", this.imagesDb.urls.regular, ""),
        user = User(
            this.userDb!!.userName,
            this.userDb.name,
            this.userDb.twitterUsername,
            this.userDb.portfolioUrls,
            ProfileImage("", this.userDb.profileImage),
            this.userDb.instagramUsername
        ),
        width = this.imagesDb.width,
        height = this.imagesDb.height,
        color = this.imagesDb.color,
        description = this.imagesDb.description,
        createdAt = this.imagesDb.createdAt,
        tags = this.imagesDb.tags
    )
}

fun SearchEntity.asSearchDb(): SearchDb {
    return SearchDb(
        resultAmount = this.resultAmount,
        searchDate = this.searchDate,
        isFavorite = this.isFavorite,
        searchQuery = this.searchQuery
    )
}

//
//fun List<ImagesDb>.asImagesDb(): List<Results> {
//    return map {
//        Results(
//            id = it.imageId,
//            user = User("", "", "", "", ProfileImage("", ""), ""),
//            urls = it.urls.asUrlModel(),
//            width = it.width,
//            height = it.height,
//            color = it.color,
//            description = it.description,
//            createdAt = it.createdAt,
//            tags = null
//        )
//    }
//}
//
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
//
//fun UserDb.asResultModel(): User {
//    return User(
//        userName = this.userName,
//        name = this.name,
//        twitterUsername = this.twitterUsername,
//        portfolioUrl = this.portfolioUrls,
//        profileImage = this.profileImage?.asResultModel()!!,
//        instagramUsername = this.instagramUsername
//    )
//}
//
//fun String.asResultModel(): ProfileImage {
//    return ProfileImage("", this)
//}
//
//fun String.asUrlModel(): Urls {
//    return Urls("", "", this, "")
//}
//
////@JvmName("asEmployeeModelImagesDb")
////fun List<ImagesDb>.asEmployeeModel(): List<Results> {
////    return map {
////        Results(
////            id = 0,
////            urls = it.urls,
////            user = it.user,
////            width = it.width,
////            height = it.height,
////            color = it.color,
////            description = it.description,
////            createdAt = it.createdAt,
////            tags = null
////        )
////    }
////}
////fun UserDb.asUserSingleItem(): UserDb {
////    return UserDb(
////        id = 0,
////        userName = this.userName,
////        twitterUsername = this.twitterUsername,
////        portfolioUrls = this.portfolioUrls,
////        profileImage = this.profileImage,
////        instagramUsername = this.instagramUsername
////    )
////}
//
////fun Results.asEmployeeSingleItem(): ImagesDb {
////    return ImagesDb(
////        imageId = this.id,
////        userId = 0,
////        urls = this.urls,
////        user = this.user,
////        width = this.width,
////        height = this.height,
////        color = this.color,
////        description = this.description,
////        createdAt = this.createdAt,
////        tags = null
////    )
////
////}
