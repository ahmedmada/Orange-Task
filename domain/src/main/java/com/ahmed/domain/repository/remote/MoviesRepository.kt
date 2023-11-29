package com.ahmed.domain.repository.remote

import com.ahmed.domain.model.photo.Images
import com.ahmed.domain.model.photo.Photos
import com.ahmed.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getPhotos(title: String): Flow<DataState<Images<Photos>>>

}