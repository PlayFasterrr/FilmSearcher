package com.example.filmsearcher.data.room.dao

import androidx.room.*
import com.example.filmsearcher.data.room.entity.FilmDB

@Dao
interface FilmsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(films: List<FilmDB>?)

    @Query("SELECT * FROM films")
    fun getLastMovies(): List<FilmDB>

    @Query("DELETE from films")
    fun deleteAll()
}