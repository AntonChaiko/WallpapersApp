package com.example.data.remote.api

import com.example.data.model.ImageEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.http.Headers


private const val BASE_URL = "https://api.unsplash.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface ImageApiService {
    @Headers("Authorization: Client-ID wsjIVShEt25_9q8d2rASTVQHSsnd_Ou2hc1Kk8lhr7o")
    @GET("photos")
    suspend fun getImages(): List<ImageEntity>
}

object ImageApi {
    val retrofitService: ImageApiService by lazy {
        retrofit.create(ImageApiService::class.java)
    }
}