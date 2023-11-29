package com.ahmed.domain.model.movie

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Movie(

    @SerializedName("title") var title: String? = null,
    @SerializedName("year") var year: Int? = null,
    @SerializedName("cast") var cast: ArrayList<String>? = arrayListOf(),
    @SerializedName("genres") var genres: ArrayList<String>? = arrayListOf(),
    @SerializedName("rating") var rating: Int? = null

) : Serializable