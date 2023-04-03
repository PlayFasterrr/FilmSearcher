package com.example.filmsearcher.domain.repository

import com.example.filmsearcher.domain.models.Film
import com.example.filmsearcher.old.room.entity.FilmsDB

interface FromDBEntityConverter {
    fun fromEntity(list: List<FilmsDB>): ArrayList<Film>
}
