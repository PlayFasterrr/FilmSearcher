package com.example.filmsearcher.domain.usecase

import com.example.filmsearcher.domain.models.Film
import com.example.filmsearcher.domain.repository.FromDBEntityConverter
import com.example.filmsearcher.old.room.entity.FilmsDB

class FromDBEntity(private val fromDBEntityConverter: FromDBEntityConverter) {
    fun execute(list: List<FilmsDB>): ArrayList<Film> {
        return fromDBEntityConverter.fromEntity(list)
    }
}