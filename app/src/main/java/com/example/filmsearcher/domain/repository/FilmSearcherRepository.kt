package com.example.filmsearcher.domain.repository

import com.example.filmsearcher.data.models.Film
import com.example.filmsearcher.data.models.SearchResponse
import com.example.filmsearcher.data.models.WikiResponse
import com.example.filmsearcher.data.room.entity.FilmDB
import retrofit2.Response

interface FilmSearcherRepository {

    suspend fun getFilmsFromAPI(expression: String): Response<SearchResponse>

    suspend fun getFilmWikiFromAPI(id: String): Response<WikiResponse>

    suspend fun insertFilmsToDB(films: List<Film>): Boolean

    suspend fun readFilmsFromDB(): ArrayList<FilmDB>

    suspend fun clearDB(): Boolean

    suspend fun onClickFilm()

    suspend  fun toDBEntity(films: MutableList<Film>): List<FilmDB>

    suspend fun fromDBEntity(filmsDB : MutableList<FilmDB>): List<Film>
}