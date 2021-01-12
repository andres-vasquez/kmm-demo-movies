package com.github.andresvasquez.topmovies.androidApp.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.andresvasquez.topmovies.androidApp.ui.base.BaseFragment
import com.github.andresvasquez.topmovies.androidApp.databinding.FragmentListBinding
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : BaseFragment() {
    override val viewModel: MovieListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = MovieListAdapter(MovieClickListener { movie ->
            val navController = findNavController()
            navController.navigate(
                MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(
                    movie
                )
            )
        })

        // Sets the adapter of the RecyclerView
        binding.moviesRecycler.adapter = adapter
        return binding.root
    }
}