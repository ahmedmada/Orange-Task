package com.ahmed.domain.use_case.movies

import com.ahmed.domain.repository.local.LocalRepository
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend operator fun invoke(search: String) = localRepository.getMovies(search)
}