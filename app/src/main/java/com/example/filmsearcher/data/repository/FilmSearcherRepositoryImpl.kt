package com.example.filmsearcher.data.repository

import com.example.filmsearcher.data.room.dao.FilmsDao
import com.example.filmsearcher.data.models.Film
import com.example.filmsearcher.data.models.SearchResponse
import com.example.filmsearcher.data.models.WikiResponse
import com.example.filmsearcher.data.room.entity.FilmDB
import com.example.filmsearcher.domain.repository.FilmSearcherApiService
import com.example.filmsearcher.domain.repository.FilmSearcherRepository
import retrofit2.Response

class FilmSearcherRepositoryImpl(
    private val apiService: FilmSearcherApiService,
    private val movieDao: FilmsDao
) : FilmSearcherRepository {

    override suspend fun getFilmsFromAPI(expression: String): Response<SearchResponse> {
        return apiService.getFilms(expression)
    }

    override suspend fun getFilmWikiFromAPI(id: String): Response<WikiResponse> {
        return apiService.getFilmWiki(id)
    }

    override suspend fun insertFilmsToDB(films: List<Film>?): Boolean {
        movieDao.insertAllMovies(toDBEntity(films))
        return true
    }

    override suspend fun readFilmsFromDB(): List<Film> {
        return fromDBEntity(movieDao.getLastMovies())
    }

    override suspend fun clearDB(): Boolean {
        movieDao.deleteAll()
        return true
    }

    override suspend fun toDBEntity(films: List<Film>?): List<FilmDB> {
        val filmsDBList: MutableList<FilmDB> = mutableListOf()
        if (films != null) {
            for (item in films) {
                val filmDB = FilmDB(
                    id = item.id ?: "1",
                    resultType = item.resultType ?: "",
                    image = item.image ?: "",
                    title = item.title ?: "",
                    description = item.description ?: ""
                )
                filmsDBList.add(filmDB)
            }
        }
        return filmsDBList.toList()
    }

    override suspend fun fromDBEntity(filmsDB: List<FilmDB>): List<Film> {
        val filmsList: MutableList<Film> = mutableListOf()
        for (item in filmsDB) {
            val film = Film(
                id = item.id,
                resultType = item.resultType,
                image = item.image,
                title = item.title,
                description = item.description
            )
            filmsList.add(film)
        }
        return filmsList.toList()
    }
}