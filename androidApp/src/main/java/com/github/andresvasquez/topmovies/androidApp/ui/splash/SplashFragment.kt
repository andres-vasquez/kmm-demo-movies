package com.github.andresvasquez.topmovies.androidApp.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.andresvasquez.topmovies.androidApp.ui.base.BaseFragment
import com.github.andresvasquez.topmovies.androidApp.databinding.FragmentSplashBinding
import com.github.andresvasquez.topmovies.androidApp.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment() {
    override val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSplashBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setHasOptionsMenu(true)
        return binding.root
    }
}