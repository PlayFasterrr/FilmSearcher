package com.example.filmsearcher.room.dao

import androidx.room.*
import com.example.filmsearcher.room.entity.FilmsDB

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(films: ArrayList<FilmsDB>)

    @Query("SELECT * FROM films")
    fun getLastMovies(): List<FilmsDB>

    @Query("DELETE from films")
    fun deleteAll()
}