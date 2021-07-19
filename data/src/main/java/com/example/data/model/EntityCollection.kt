package com.example.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Results(
    @Json(name = "id")
    val id: String,
    @Json(name = "urls")
    val urls: Urls,
    @Json(name = "user")
    val user: User,
    @Json(name = "width")
    val width: Int,
    @Json(name = "height")
    val height: Int,
    @Json(name = "color")
    val color: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "tags")
    val tags: List<Tags>
) : Parcelable

@Parcelize
data class Tags(
    @Json(name = "title")
    val title: String
) : Parcelable {

}

@Parcelize
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
) : Parcelable {

}

@Parcelize
data class ProfileImage(
    @Json(name = "small")
    val small: String?,
    @Json(name = "medium")
    val medium: String?,
) : Parcelable


@Parcelize
data class Urls(
    @Json(name = "raw")
    val raw: String,
    @Json(name = "full")
    val full: String,
    @Json(name = "regular")
    val regular: String,
    @Json(name = "small")
    val small: String

) : Parcelable

//"updated_at":"2021-07-14T00:31:04-04:00",
