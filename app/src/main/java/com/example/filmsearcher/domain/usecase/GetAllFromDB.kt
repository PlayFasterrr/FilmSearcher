package com.example.filmsearcher.domain.usecase

import com.example.filmsearcher.domain.repository.DBInterface
import com.example.filmsearcher.old.room.entity.FilmsDB

class GetAllFromDB(private val dataBaseInterface: DBInterface) {
    fun execute(): List<FilmsDB> {
        return dataBaseInterface.getLastMovies()
    }
}