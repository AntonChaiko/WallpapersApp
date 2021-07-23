package com.example.data.db.imagesdb

import androidx.room.TypeConverter
import com.example.data.model.ProfileImage
import com.example.data.model.Tags
import com.example.data.model.Urls

class Convertors {

    @TypeConverter
    fun urlsToString(urls: Urls): String {
        return urls.regular
    }

    @TypeConverter
    fun stringToUrls(url: String): Urls {
        return Urls("", "", url, "")
    }

    @TypeConverter
    fun profileImageToString(profileImage: ProfileImage): String? {
        return profileImage.medium
    }

    @TypeConverter
    fun stringToProfileImage(profileImage: String): ProfileImage {
        return ProfileImage("", profileImage)
    }

    @TypeConverter
    fun tagsToString(tags: List<Tags>): String? {
        return tags[0].title
    }

    @TypeConverter
    fun stringToTags(tags: String): List<Tags> {
        return listOf(Tags(tags))
    }
}