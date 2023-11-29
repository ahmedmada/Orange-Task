package com.ahmed.orangechallenge.ui.home

import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmed.domain.model.movie.Movie
import com.ahmed.orangechallenge.databinding.FragmentHomeBinding
import com.ahmed.orangechallenge.ui.base.BaseFragment
import com.ahmed.orangechallenge.ui.base.applyCommonSideEffects
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    private val movieList = ArrayList<Movie>()

    private val movieAdapter by lazy {
        MovieAdapter {
            onMovieClicked(movie = it)
        }
    }

    private fun onMovieClicked(movie: Movie) {

        val action = HomeFragmentDirections.actionToDetailsFragment(
            movie = movie
        )
        findNavController().navigate(action)
    }


    override fun afterCreateView() {
        viewModel.getMovies()
        setMoviesRV()
        initSearchView()
    }

    override fun handleObservers() {
        observeHome()
    }

    private fun setMoviesRV() {
        binding.rvMovies.adapter = movieAdapter
    }

    private fun initSearchView() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                movieAdapter.getFilter().filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                movieAdapter.getFilter().filter(newText);
                return true
            }

        })
    }

    private fun observeHome() {
        observe {

            viewModel.movies.collectLatest {

                it.applyCommonSideEffects(this) { response ->
                    response.let { data ->
                        if (data.isNotEmpty()) {
                            setMovies(movies = data)
                        }
                    }
                }
            }

        }
    }

    private fun setMovies(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)

        movieAdapter.submitList(movieList)
        binding.rvMovies.scheduleLayoutAnimation()
    }

}
