package com.example.filmsearcher.domain.usecase

import com.example.filmsearcher.domain.models.Film
import com.example.filmsearcher.domain.repository.ToDBEntityConverter
import com.example.filmsearcher.old.room.entity.FilmsDB

class ToDBEntity(private val toDBEntityConverter: ToDBEntityConverter) {
    fun execute(list: ArrayList<Film>): ArrayList<FilmsDB> {
        return toDBEntityConverter.toEntity(list)
    }

}