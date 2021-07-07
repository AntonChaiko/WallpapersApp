package com.example.data.remote.api

import com.example.data.constants.API_KEY
import com.example.data.model.BaseEntity
import com.example.data.model.ImageEntity
import com.example.data.model.Results
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.http.Headers
import retrofit2.http.Query



interface ImageApiService {

    @Headers("Authorization: $API_KEY")
    @GET("photos")
    suspend fun getImages(): List<ImageEntity>

    @Headers("Authorization: $API_KEY")
    @GET("search/collections")
    suspend fun getCollection(@Query("query") categoryName: String): BaseEntity

}

