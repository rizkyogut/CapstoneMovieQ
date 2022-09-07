package com.q.capstonemovieq.core.data


import com.q.capstonemovieq.BuildConfig
import com.q.capstonemovieq.core.data.source.local.LocalDataSource
import com.q.capstonemovieq.core.data.source.remote.RemoteDataSource
import com.q.capstonemovieq.core.data.source.remote.network.ApiResponse
import com.q.capstonemovieq.core.data.source.remote.response.MovieResponse
import com.q.capstonemovieq.core.domain.model.Movie
import com.q.capstonemovieq.core.domain.repository.IMovieRepository
import com.q.capstonemovieq.core.utils.AppExecutors
import com.q.capstonemovieq.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : IMovieRepository {

    override fun getAllMovies(key: String): Flow<Resource<List<Movie>>> = flow()

    override fun getAllMoviePlaying(key: String): Flow<Resource<List<Movie>>> = flow()

    override fun getAllMovieUpComing(key: String): Flow<Resource<List<Movie>>> = flow()

    override fun getAllMovieTopRated(key: String): Flow<Resource<List<Movie>>> = flow()

    private fun flow() = object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
        override fun loadFromDB(): Flow<List<Movie>> {
            return localDataSource.getAllMovie().map {
                DataMapper.mapEntitiesToDomain(it)
            }
        }

        override fun shouldFetch(data: List<Movie>?): Boolean = true

        override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
            remoteDataSource.getAllMovie(key = BuildConfig.API_KEY)

        override suspend fun saveCallResult(data: List<MovieResponse>) {
            val movieList = DataMapper.mapResponsesToEntities(data)
            localDataSource.insertMovie(movieList)
        }
    }.asFlow()

    override fun getFavoriteMovie(key: String): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movieEntity, state)
        }
    }
}