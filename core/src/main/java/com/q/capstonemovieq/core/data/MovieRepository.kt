package com.q.capstonemovieq.core.data

import com.q.capstonemovieq.core.BuildConfig.API_KEY
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

    override fun getAllMovies(key: String): Flow<Resource<List<Movie>>> =
        object : com.q.capstonemovieq.core.data.NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie(key = API_KEY)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getAllMoviePlaying(key: String): Flow<Resource<List<Movie>>> =
        object : com.q.capstonemovieq.core.data.NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMoviePlaying(key = API_KEY)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getAllMovieTopRated(key: String): Flow<Resource<List<Movie>>> =
        object : com.q.capstonemovieq.core.data.NetworkBoundResource<List<Movie>, List<MovieResponse>>(){
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getTopRatedMovies(key = API_KEY)

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