package com.github.andresvasquez.topmovies.androidApp.ui.movielist

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.andresvasquez.topmovies.androidApp.R
import com.github.andresvasquez.topmovies.shared.data.source.PopularMovie
import com.github.andresvasquez.topmovies.shared.data.source.User
import com.github.andresvasquez.topmovies.shared.utils.Constants

@BindingAdapter("userImage")
fun bindMUserImage(imageView: ImageView, user: User?) {
    if (user != null) {
        Glide.with(imageView)
            .load(user.photo)
            .circleCrop()
            .error(R.drawable.ic_baseline_android_24)
            .into(imageView)
    } else {
        Glide.with(imageView).load(R.drawable.ic_baseline_android_empty).circleCrop()
            .into(imageView)
    }
}

@BindingAdapter("movieRanking")
fun bindMovieRanking(ranking: RatingBar, movie: PopularMovie?) {
    if (movie != null) {
        ranking.rating = movie.rating.toFloat()
    }
}

@BindingAdapter("moviePicture")
fun bindMovieImage(imageView: ImageView, movie: PopularMovie?) {
    if (movie != null) {

        Glide.with(imageView)
            .load(Constants.BASE_IMAGE_URL + movie.image)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.contentDescription =
                        imageView.context.getString(R.string.movie_description_error)
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?, model: Any?, target: Target<Drawable>?,
                    dataSource: DataSource?, isFirstResource: Boolean
                ): Boolean {
                    imageView.contentDescription =
                        imageView.context.getString(
                            R.string.movie_description,
                            movie.displayName
                        )
                    return false
                }

            })
            .into(imageView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<PopularMovie>?) {
    val adapter = recyclerView.adapter as MovieListAdapter
    adapter.submitList(data)
}

@BindingAdapter("loadingVisibility")
fun bindLoadingVisibility(view: View, visible: Boolean) {
    if (visible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}
