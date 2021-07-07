package com.example.domain.model.dto

data class ImageEntityDto(
    val id: String,
    val links: Urls,
    val userAbout: UserAbout
)

data class Urls(
    val full: String
)

data class UserAbout(
    val name: String,
    val twitter_username: String?,
    val portfolio_url: String?,
    val bio: String?,
    val profile_image: ProfileImage?,
    val social: Social?

)

data class ProfileImage(
    val small: String?,
)

data class Social(
    val instagram_username: String?,
    val portfolio_url: String?,
    val twitter_username: String?
)