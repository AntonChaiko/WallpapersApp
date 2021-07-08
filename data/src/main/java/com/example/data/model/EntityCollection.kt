package com.example.data.model

import com.squareup.moshi.Json

data class Results(
    @Json(name = "id")
    val id: String,
    @Json(name = "urls")
    val urls: Urls,
    @Json(name = "user")
    val user: User,
    )

data class User(
    @Json(name = "username")
    val userName: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "twitter_username")
    val twitterUsername: String?,
    @Json(name = "portfolio_url")
    val portfolioUrl: String?,
    @Json(name = "profile_image")
    val profileImage: ProfileImage,
    @Json(name = "instagram_username")
    val instagramUsername: String?
) {

}

data class ProfileImage(
    @Json(name = "small")
    val small: String?,
    @Json(name = "medium")
    val medium: String?,
)

data class Urls(
    @Json(name = "raw")
    val raw: String,
    @Json(name = "full")
    val full: String,
    @Json(name = "regular")
    val regular: String,
    @Json(name = "small")
    val small: String

)