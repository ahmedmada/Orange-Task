package com.ahmed.domain.use_case.photos

import com.ahmed.domain.repository.remote.MoviesRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotosUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(title: String) = flow { emitAll(moviesRepository.getPhotos(title)) }
}