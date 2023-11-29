package com.ahmed.domain.repository.local

import com.ahmed.domain.model.movie.Movie
import com.ahmed.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun getMovies(search: String?): Flow<DataState<ArrayList<Movie>>>
}