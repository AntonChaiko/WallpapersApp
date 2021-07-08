package com.example.data.remote.api

import androidx.annotation.IntRange
import com.example.data.constants.API_KEY
import com.example.data.constants.DEFAULT_PAGE_SIZE
import com.example.data.constants.MAX_PAGE_SIZE
import retrofit2.http.GET

import retrofit2.http.Headers
import retrofit2.http.Query


interface ImageApiService {

    @Headers("Authorization: $API_KEY")
    @GET("search/photos")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("per_page") @IntRange(
            from = 1,
            to = MAX_PAGE_SIZE.toLong()
        ) perPage: Int = DEFAULT_PAGE_SIZE
    ): ImageSearchResponse


}

