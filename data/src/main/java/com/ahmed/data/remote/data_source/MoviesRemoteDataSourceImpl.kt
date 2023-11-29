package com.ahmed.data.remote.data_source

import com.ahmed.data.data_source.remote.MoviesDataSource
import com.ahmed.data.remote.end_points.MoviesEndPoints
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moviesEndPoints: MoviesEndPoints
) : MoviesDataSource {

    override suspend fun getPhotos(title: String) = moviesEndPoints.getPhotos(title)

}