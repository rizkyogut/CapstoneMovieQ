package com.q.capstonemovieq.home.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.q.capstonemovieq.BuildConfig
import com.q.capstonemovieq.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowItemViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {

    val popularMovieList = movieUseCase.getAllMovie(BuildConfig.API_KEY).asLiveData()
    val playingMovieList = movieUseCase.getAllMoviePlaying(BuildConfig.API_KEY).asLiveData()
    val upcomingMovieList = movieUseCase.getAllMovieUpComing(BuildConfig.API_KEY).asLiveData()
    val topRatedMovieList = movieUseCase.getAllMovieTopRated(BuildConfig.API_KEY).asLiveData()
}