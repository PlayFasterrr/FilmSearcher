package com.example.filmsearcher.domain.repository

import com.example.filmsearcher.domain.models.Film
import com.example.filmsearcher.domain.models.SearchResponse
import com.example.filmsearcher.domain.models.WikiResponse
import retrofit2.Response

interface FilmSearcherRepository {

    suspend fun getFilms(expression: String): Response<SearchResponse>

    suspend fun getFilmWiki(id: String): Response<WikiResponse>

    suspend fun storeFilms(films: List<Film>): Boolean
}