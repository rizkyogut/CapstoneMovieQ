package com.q.capstonemovieq.core.data.source.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovie(): Flow<List<com.q.capstonemovieq.core.data.source.local.entity.MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<com.q.capstonemovieq.core.data.source.local.entity.MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<com.q.capstonemovieq.core.data.source.local.entity.MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: com.q.capstonemovieq.core.data.source.local.entity.MovieEntity)
}