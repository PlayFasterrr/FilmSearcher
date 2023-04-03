package com.example.filmsearcher.domain.usecase

import com.example.filmsearcher.domain.models.SearchResponse

class GetFilms(private val filmApi: FilmApi) {

    suspend fun execute(expression: String): SearchResponse {
        return filmApi.getFilms(expression)
    }
}