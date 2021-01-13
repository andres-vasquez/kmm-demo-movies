package com.github.andresvasquez.topmovies.androidApp.ui.moviedetail

import androidx.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.andresvasquez.topmovies.androidApp.ui.base.BaseFragment
import com.github.andresvasquez.topmovies.androidApp.R
import com.github.andresvasquez.topmovies.androidApp.databinding.FragmentDetailBinding
import com.github.andresvasquez.topmovies.androidApp.utils.ParcelablePopularMovie
import com.github.andresvasquez.topmovies.androidApp.utils.asOriginal
import com.github.andresvasquez.topmovies.shared.data.source.PopularMovie
import com.github.andresvasquez.topmovies.shared.data.source.remote.model.Genre
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment() {
    override val viewModel: MovieDetailViewModel by viewModel()

    private val selectedMovie: PopularMovie by lazy {
        val args = MovieDetailFragmentArgs.fromBundle(requireArguments())
        val movie: ParcelablePopularMovie = args.movie
        movie.asOriginal()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.selectedMovie = selectedMovie

        viewModel.genres.observe(viewLifecycleOwner, Observer<List<Genre>> { data ->
            if (!data.isNullOrEmpty()) {
                val chipGroup = binding.genreList
                val inflator = LayoutInflater.from(chipGroup.context)

                val children = data.map { genre ->
                    val chip = inflator.inflate(R.layout.genre, chipGroup, false) as Chip
                    chip.text = genre.name
                    chip.tag = genre.name
                    chip
                }

                chipGroup.removeAllViews()
                for (chip in children) {
                    chipGroup.addView(chip)
                }
            }
        })


        viewModel.loadGenres(selectedMovie)
        return binding.root
    }
}