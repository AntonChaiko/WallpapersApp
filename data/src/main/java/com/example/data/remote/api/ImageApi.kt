package com.example.data.remote.api

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


private const val BASE_URL = "https://api.unsplash.com/"
private const val API_KEY = "Client-ID wsjIVShEt25_9q8d2rASTVQHSsnd_Ou2hc1Kk8lhr7o"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface ImageApiService {

    @Headers("Authorization: $API_KEY")
    @GET("photos")
    suspend fun getImages(): List<ImageEntity>

    @Headers("Authorization: $API_KEY")
    @GET("search/collections")
    suspend fun getCollection(@Query("query") categoryName: String): BaseEntity

}

object ImageApi {
    val retrofitService: ImageApiService by lazy {
        retrofit.create(ImageApiService::class.java)
    }
}