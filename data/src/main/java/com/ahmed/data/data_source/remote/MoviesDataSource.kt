package com.ahmed.data.data_source.remote

import com.ahmed.domain.model.photo.Images
import com.ahmed.domain.model.photo.Photos


interface MoviesDataSource {

    suspend fun getPhotos(title: String): Images<Photos>

}