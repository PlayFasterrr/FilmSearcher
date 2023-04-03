package com.example.filmsearcher.domain.usecase

import com.example.filmsearcher.domain.repository.DBInterface

class DeleteDataInDB(private val dataBaseInterface: DBInterface) {
    fun execute() {
        dataBaseInterface.deleteAll()
    }
}