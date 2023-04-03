package com.example.filmsearcher.domain.repository

import com.example.filmsearcher.data.repository.FilmSearcherApiService
import com.example.filmsearcher.data.repository.dataBase.MovieDao
import com.example.filmsearcher.domain.models.Film
import com.example.filmsearcher.domain.models.SearchResponse
import com.example.filmsearcher.domain.models.WikiResponse
import retrofit2.Response

class FilmSearcherRepositoryImpl(
    val apiService: FilmSearcherApiService,
    val movieDao: MovieDao
): FilmSearcherRepository, {

    override suspend fun getFilms(expression: String): Response<SearchResponse> {
        return apiService.getFilms(expression)
    }

    override suspend fun getFilmWiki(id: String): Response<WikiResponse> {
        return apiService.getFilmWiki(id)
    }

    override suspend fun storeFilms(films: List<Film>): Boolean {
        TODO("Not yet implemented")
    }

//    override suspend fun getSerials(expression: String): Response<SearchResponse> {
//        return apiService.getFilms(expression)
//    }
//
//    override suspend fun getSerialmWiki(id: String): Response<WikiResponse> {
//        return apiService.getFilmWiki(id)
//    }
//
//    override suspend fun storeSerials(films: List<Film>): Boolean {
//        TODO("Not yet implemented")
//    }
}