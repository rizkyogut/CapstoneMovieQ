package com.q.capstonemovieq.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.q.capstonemovieq.BuildConfig
import com.q.capstonemovieq.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {

    val favoriteMovie = movieUseCase.getFavoriteMovie(BuildConfig.API_KEY).asLiveData()
}

