package com.ahmed.data.data_source.local

import com.ahmed.domain.model.movie.Movies

interface LocalDataSource {
    suspend fun getMovies(): Movies
}