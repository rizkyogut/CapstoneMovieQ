package com.q.capstonemovieq.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.q.capstonemovieq.R
import com.q.capstonemovieq.core.domain.model.Movie
import com.q.capstonemovieq.core.utils.setImage
import com.q.capstonemovieq.databinding.ActivityDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailMovieBinding

    private val detailMovieViewModel: DetailMovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        if (detailMovie != null) {
            showDetailMovie(detailMovie)
        }
    }

    private fun showDetailMovie(data: Movie) {
        with(binding) {
            data.backdropPath.let { appBar.ivBackdropPath.setImage(it) }
            data.posterPath.let { ivPosterDetail.setImage(it) }
            tvTitleDetail.text = data.title
            tvOverviewDetail.text = data.overview
            tvReleaseDateDetail.text = data.releaseDate
            tvRatingDetail.text = data.voteAverage.toString()
            tvRatingCountDetail.text = data.voteCount.toString()
            tvPopularityDetail.text = data.popularity.toString()

            var statusFavorite = data.isFavorite
            setStatusFavorite(statusFavorite)

            fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteDetail(data, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_bookmark))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_bookmark_border))
        }
    }
}