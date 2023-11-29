package com.ahmed.orangechallenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.domain.model.movie.Movie
import com.ahmed.domain.use_case.movies.MoviesUseCase
import com.ahmed.domain.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {

    private var _movies =
        MutableStateFlow<DataState<ArrayList<Movie>>>(DataState.Idle)
    val movies = _movies.asStateFlow()

    fun getMovies() {
        viewModelScope.launch {
            moviesUseCase("").collect {
                _movies.value = it
            }
        }
    }
}