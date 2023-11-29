package com.ahmed.domain.model.movie

import com.google.gson.annotations.SerializedName


data class Movies (

  @SerializedName("movies" ) var movies : ArrayList<Movie> = arrayListOf()

)