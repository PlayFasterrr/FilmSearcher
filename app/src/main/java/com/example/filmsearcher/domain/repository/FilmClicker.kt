package com.example.filmsearcher.domain.repository

import com.example.filmsearcher.data.models.Film

interface FilmClicker {
    fun clickFilm(film: Film)
}