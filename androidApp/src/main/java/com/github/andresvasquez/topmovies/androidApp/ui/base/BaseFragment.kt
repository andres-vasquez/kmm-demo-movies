package com.github.andresvasquez.topmovies.androidApp.ui.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.kmmapp.androidApp.ui.base.BaseViewModel
import com.example.kmmapp.androidApp.ui.base.NavigationCommand

abstract class BaseFragment : Fragment() {
    abstract val viewModel: BaseViewModel

    override fun onStart() {
        super.onStart()
        viewModel.showErrorMessage.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
        viewModel.showToast.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
        viewModel.showSnackBar.observe(this, Observer {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
        })
        viewModel.showSnackBarInt.observe(this, Observer {
            Snackbar.make(requireView(), getString(it), Snackbar.LENGTH_LONG).show()
        })

        viewModel.navigationCommand.observe(this, Observer { command ->
            when (command) {
                is NavigationCommand.To -> findNavController().navigate(command.directions)
                is NavigationCommand.Back -> findNavController().popBackStack()
                is NavigationCommand.BackTo -> findNavController().popBackStack(
                    command.destinationId,
                    false
                )
            }
        })
    }
}