package com.example.data.remote.api

import com.example.data.model.Results
import com.squareup.moshi.Json

data class ImageSearchResponse(
    @Json(name = "total")
    val total:Long,
    @Json(name = "results")
    val items: List<Results> = emptyList()
)