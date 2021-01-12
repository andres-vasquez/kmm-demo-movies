package com.github.andresvasquez.topmovies.androidApp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.github.andresvasquez.topmovies.shared.Greeting
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.github.andresvasquez.topmovies.androidApp.databinding.ActivityMainBinding
import com.github.andresvasquez.topmovies.androidApp.databinding.NavHeaderBinding
import com.github.andresvasquez.topmovies.androidApp.utils.sendNotification
import com.github.andresvasquez.topmovies.androidApp.utils.setLocale
import com.github.andresvasquez.topmovies.shared.MoviesShared
import com.github.andresvasquez.topmovies.shared.MoviesSharedI
import com.github.andresvasquez.topmovies.shared.data.source.local.DatabaseDriverFactory
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    private var navViewHeaderBinding: NavHeaderBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shared: MoviesSharedI by inject()
        shared.getUserPrefs()?.let { setLocale(this, it.language) }

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout

        navController = this.findNavController(R.id.nav_host_fragment)
        // prevent nav gesture if not on start destination
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
            when (nd.id) {
                R.id.splashFragment -> {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                else -> {
                    updateUserInfo(binding)
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }
            }
        }

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
        binding.navView.setNavigationItemSelectedListener(this)

        updateUserInfo(binding)
        binding.lifecycleOwner = this
    }

    private fun updateUserInfo(binding: ActivityMainBinding) {
        val shared: MoviesSharedI by inject()
        shared.getUserPrefs()?.let {
            val viewHeader = binding.navView.getHeaderView(0)
            if (navViewHeaderBinding == null) {
                navViewHeaderBinding = NavHeaderBinding.bind(viewHeader)
            }
            navViewHeaderBinding!!.user = it
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        //return navController.navigateUp()
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onNavigationItemSelected(@NonNull menuItem: MenuItem): Boolean {
        drawerLayout.closeDrawers()
        val id: Int = menuItem.getItemId()
        when (id) {
            R.id.logout -> navController.navigate(R.id.loginFragment)
            R.id.about -> sendNotification(this)
        }
        return true
    }
}
