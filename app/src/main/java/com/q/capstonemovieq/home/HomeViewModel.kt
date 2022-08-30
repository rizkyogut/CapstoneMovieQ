package com.q.capstonemovieq.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.q.capstonemovieq.BuildConfig
import com.q.capstonemovieq.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {

    val movie = movieUseCase.getAllMovie(BuildConfig.API_KEY).asLiveData()
}