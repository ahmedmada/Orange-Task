package com.ahmed.domain.model.photo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photos(

    @Json(name = "page") var page: Int? = null,
    @Json(name = "pages") var pages: Int? = null,
    @Json(name = "perpage") var perpage: Int? = null,
    @Json(name = "total") var total: Int? = null,
    @Json(name = "photo") var photo: List<Photo> = arrayListOf()

)