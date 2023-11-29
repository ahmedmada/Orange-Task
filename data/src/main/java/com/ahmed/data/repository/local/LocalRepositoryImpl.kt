package com.ahmed.data.repository.local

import com.ahmed.data.data_source.local.LocalDataSource
import com.ahmed.data.util.safeLocalCall
import com.ahmed.domain.model.movie.Movie
import com.ahmed.domain.repository.local.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    LocalRepository {

    override suspend fun getMovies(search: String?) = safeLocalCall {
        localDataSource.getMovies().movies
            .sortedWith(compareBy(Movie::year, Movie::rating)).filter {
                search?.let { it1 -> it.title?.contains(it1) } ?: false
            }.reversed() as ArrayList<Movie>
    }
}