package com.ahmed.orangechallenge.ui.details

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ahmed.orangechallenge.databinding.FragmentDetailsBinding
import com.ahmed.orangechallenge.ui.base.BaseFragment
import com.ahmed.orangechallenge.ui.base.applyCommonSideEffects
import com.ahmed.orangechallenge.util.loadImageFromUri
import com.ahmed.orangechallenge.util.toGone
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val viewModel by viewModels<DetailsViewModel>()
    private val args by navArgs<DetailsFragmentArgs>()
    private val textAdapter1 by lazy {
        TextAdapter()
    }
    private val textAdapter2 by lazy {
        TextAdapter()
    }

    override fun afterCreateView() {
        viewModel.getPhotos(args.movie.title ?: "")
        initUI()
        setCastRV()
        setGenresRV()
    }

    private fun initUI() {
        binding.apply {
            title.text = args.movie.title
            years.text = args.movie.year.toString()
            rate.text = "Rating : ${args.movie.rating.toString()}"
        }

    }

    private fun setCastRV() {
        binding.rvCast.adapter = textAdapter1

        if (args.movie.cast.isNullOrEmpty())
            binding.tvCast.toGone()
        else
            args.movie.cast?.let { setCast(it) }
    }

    private fun setGenresRV() {
        binding.rvGenres.adapter = textAdapter2

        if (args.movie.genres.isNullOrEmpty())
            binding.tvGenres.toGone()
        else
            args.movie.genres?.let { setGenres(it) }

    }

    override fun handleObservers() {
        observeHome()
    }

    private fun observeHome() {
        observe {


            viewModel.photos.collectLatest {

                it.applyCommonSideEffects(this) { response ->
                    response.let { data ->

                        try {
                            binding.image.loadImageFromUri(
                                requireContext(),
                                "https://farm${data.photos?.photo?.get(0)?.farm}.static.flickr.com/${
                                    data.photos?.photo?.get(
                                        0
                                    )?.server
                                }/${data.photos?.photo?.get(0)?.id}_${data.photos?.photo?.get(0)?.secret}.jpg"
                            )

                            var selectedMovies = data.photos?.photo?.filter { it ->
                                it.title?.toLowerCase()
                                    ?.contains(args.movie.title?.toLowerCase().toString()) == true
                            }

                            if (!selectedMovies.isNullOrEmpty()) {
                                binding.colum1.text = selectedMovies[0].toString()
                                binding.colum2.text = selectedMovies[1].toString()
                            }
                        }catch (_:Exception){}
                    }
                }
            }
        }
    }

    private fun setCast(casts: List<String>) {
        textAdapter1.submitList(casts)
        binding.rvCast.scheduleLayoutAnimation()
    }

    private fun setGenres(genres: List<String>) {
        textAdapter2.submitList(genres)
        binding.rvGenres.scheduleLayoutAnimation()
    }

}
