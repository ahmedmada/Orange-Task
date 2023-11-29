package com.ahmed.data.repository.remote

import com.ahmed.data.data_source.remote.MoviesDataSource
import com.ahmed.data.util.safeApiCall
import com.ahmed.domain.repository.remote.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesDataSource: MoviesDataSource
) : MoviesRepository {

    override suspend fun getPhotos(title: String) =
        safeApiCall { moviesDataSource.getPhotos(title) }

}