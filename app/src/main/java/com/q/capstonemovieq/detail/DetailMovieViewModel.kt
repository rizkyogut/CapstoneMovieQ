package com.q.capstonemovieq.detail

import androidx.lifecycle.ViewModel
import com.q.capstonemovieq.core.domain.model.Movie
import com.q.capstonemovieq.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteDetail(movie: Movie, newStatus:Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}