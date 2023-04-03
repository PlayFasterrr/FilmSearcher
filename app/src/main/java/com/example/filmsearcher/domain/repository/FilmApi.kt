package com.example.filmsearcher.domain.repository

import com.example.filmsearcher.domain.models.SearchResponse

interface FilmApi {
    suspend fun getFilms(expression: String): SearchResponse
}