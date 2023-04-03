package com.example.filmsearcher.data.repository

import com.example.filmsearcher.domain.models.Film
import com.example.filmsearcher.domain.repository.FromDBEntityConverter
import com.example.filmsearcher.old.room.entity.FilmsDB

class FromDBEntityImpl : FromDBEntityConverter {
    override fun fromEntity(list: List<FilmsDB>): ArrayList<Film> {
        val filmsList: ArrayList<Film> = arrayListOf()
        for (item in list) {
            val movie = Film(
                id = item.id,
                resultType = item.resultType,
                image = item.image,
                title = item.title,
                description = item.description
            )
            filmsList.add(movie)
        }
        return filmsList
    }
}