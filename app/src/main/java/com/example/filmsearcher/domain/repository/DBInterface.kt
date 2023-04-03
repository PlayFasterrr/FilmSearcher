package com.example.filmsearcher.domain.repository

import com.example.filmsearcher.old.room.entity.FilmsDB

interface DBInterface {

    fun insertAllMovies(films: ArrayList<FilmsDB>)

    fun getLastMovies(): List<FilmsDB>

    fun deleteAll()

}