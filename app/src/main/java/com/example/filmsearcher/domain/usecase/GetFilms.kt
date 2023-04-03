package com.example.filmsearcher.domain.usecase

import com.example.filmsearcher.domain.models.SearchResponse
import com.example.filmsearcher.domain.repository.FilmApi

class GetFilms(private val filmApi: FilmApi) {

    suspend fun execute(expression: String): SearchResponse {
        return filmApi.getFilms(expression)
    }
}