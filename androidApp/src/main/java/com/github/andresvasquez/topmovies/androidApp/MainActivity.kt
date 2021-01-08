package com.github.andresvasquez.topmovies.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.andresvasquez.topmovies.shared.Greeting
import android.widget.TextView
import android.widget.Toast
import com.github.andresvasquez.topmovies.shared.MoviesShared
import com.github.andresvasquez.topmovies.shared.data.source.local.DatabaseDriverFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    private val mainScope = MainScope()

    private val sdk = MoviesShared(DatabaseDriverFactory(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()
        displayMovies(true);
    }

    private fun displayMovies(needReload: Boolean) {
        mainScope.launch {
            kotlin.runCatching {
                sdk.getPopularMovies()
            }.onSuccess {
                Log.e("Popular Movies Size", "${it.size}")
            }.onFailure {
                Log.e("Popular Movies error", "${it.message}", it)
                Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}
