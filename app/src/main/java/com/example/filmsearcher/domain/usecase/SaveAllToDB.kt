package com.example.filmsearcher.domain.usecase

import com.example.filmsearcher.domain.repository.DBInterface
import com.example.filmsearcher.old.room.entity.FilmsDB

class SaveAllToDB(private val dataBaseInterface: DBInterface) {
    fun execute(films: ArrayList<FilmsDB>) {
        dataBaseInterface.insertAllMovies(films)
    }
}