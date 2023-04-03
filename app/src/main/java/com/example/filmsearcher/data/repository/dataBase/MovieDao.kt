package com.example.filmsearcher.data.repository.dataBase

import androidx.room.*
import com.example.filmsearcher.domain.repository.DBInterface
import com.example.filmsearcher.old.room.entity.FilmsDB

@Dao
interface MovieDao : DBInterface {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertAllMovies(films: ArrayList<FilmsDB>)

    @Query("SELECT * FROM films")
    override fun getLastMovies(): List<FilmsDB>

    @Query("DELETE from films")
    override fun deleteAll()
}