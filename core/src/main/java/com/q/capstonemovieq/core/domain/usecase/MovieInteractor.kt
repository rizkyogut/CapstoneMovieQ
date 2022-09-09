package com.q.capstonemovieq.core.domain.usecase

import com.q.capstonemovieq.core.data.Resource
import com.q.capstonemovieq.core.domain.model.Movie
import com.q.capstonemovieq.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {

    override fun getAllMovie(key: String): Flow<Resource<List<Movie>>> =
        movieRepository.getAllMovies(key)

    override fun getAllMoviePlaying(key: String): Flow<Resource<List<Movie>>> =
        movieRepository.getAllMoviePlaying(key)

    override fun getAllMovieTopRated(key: String): Flow<Resource<List<Movie>>> =
        movieRepository.getAllMovieTopRated(key)

    override fun getFavoriteMovie(key: String): Flow<List<Movie>> =
        movieRepository.getFavoriteMovie(key)

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)

    }