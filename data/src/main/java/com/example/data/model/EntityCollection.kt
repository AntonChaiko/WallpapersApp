package com.example.data.model

import com.squareup.moshi.Json

data class BaseEntity(
    @Json(name = "total")
    val total : Int,
    @Json(name = "total_pages")
    val total_pages : Int,
    @Json(name = "results")
    val results: List<Results>
)

data class Results(
    @Json(name = "id")
    val id: String,

    /*   val title : String,
       val description : String,
       val published_at : String,
       val last_collected_at : String,
       val updated_at : String,
       val curated : Boolean,
       val featured : Boolean,
       val total_photos : Int,
       val private : Boolean,
       val share_share_key : String,
       val tags : List<Tags>,
       val links: Links,
    val user: User,
    val cover_photo: Cover_photo,
    val preview_photos: List<Preview_photos>*/
    @Json(name = "cover_photo")
    val cover_photo: CoverPhoto,
)

data class CoverPhoto(

/*val id : String,
    val created_at : String,
    val updated_at : String,
    val promoted_at : String,
    val width : Int,
    val height : Int,
    val color : String,
    val blur_hash : String,
    val description : String,
    val alt_description : String,*/
    @Json(name = "urls")
    val urls: Urls,
/*    val links : Links,
    val categories : List<String>,
    val likes : Int,
    val liked_by_user : Boolean,
    val current_user_collections : List<String>,
    val sponsorship : String,
    val user : User*/
)