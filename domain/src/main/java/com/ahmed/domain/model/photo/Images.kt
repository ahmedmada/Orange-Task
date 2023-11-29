package com.ahmed.domain.model.photo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Images<T>(
    @Json(name = "stat") val stat: String,
    @Json(name = "photos") val photos: T?
) : Serializable