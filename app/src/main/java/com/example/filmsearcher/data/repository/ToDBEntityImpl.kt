package com.example.filmsearcher.data.repository

import com.example.filmsearcher.domain.models.Film
import com.example.filmsearcher.domain.repository.ToDBEntityConverter
import com.example.filmsearcher.old.room.entity.FilmsDB

class ToDBEntityImpl : ToDBEntityConverter {
    override fun toEntity(list: ArrayList<Film>): ArrayList<FilmsDB> {

            val filmsDBList: ArrayList<FilmsDB> = arrayListOf()
            for (film in list) {
                val filmsDB = FilmsDB(
                    id = film.id ?: "1",
                    resultType = film.resultType ?: "",
                    image = film.image ?: "",
                    title = film.title ?: "",
                    description = film.description ?: ""
                )
                filmsDBList.add(filmsDB)
            }
            return filmsDBList

    }
}