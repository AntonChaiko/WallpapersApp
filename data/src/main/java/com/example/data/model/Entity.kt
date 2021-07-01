package com.example.data.model

import com.squareup.moshi.Json

data class ImageEntity(
    @Json(name = "id")
    val id: String,
    @Json(name = "urls")
    val links: Urls,
    @Json(name = "user")
    val userAbout: UserAbout
)

data class Urls(
    @Json(name = "full")
    val full: String

)

data class UserAbout(

    @Json(name = "name")
    val name: String,
    @Json(name = "twitter_username")
    val twitter_username: String?,
    @Json(name = "portfolio_url")
    val portfolio_url: String?,
    @Json(name = "bio")
    val bio: String?,
    @Json(name = "profile_image")
    val profile_image: ProfileImage?,
    @Json(name = "social")
    val social: Social?

)

data class ProfileImage(
    @Json(name = "small")
    val small: String?,
)

data class Social(

    @Json(name = "instagram_username")
    val instagram_username: String?,
    @Json(name = "portfolio_url")
    val portfolio_url: String?,
    @Json(name = "twitter_username")
    val twitter_username: String?
)