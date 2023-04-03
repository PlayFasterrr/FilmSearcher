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
): FilmSearcherRepository {

    override suspend fun getFilmsFromAPI(expression: String): Response<SearchResponse> {
        return apiService.getFilms(expression)
    }

    override suspend fun getFilmWikiFromAPI(id: String): Response<WikiResponse> {
        return apiService.getFilmWiki(id)
    }

    override suspend fun insertFilmsToDB(films: List<Film>): Boolean {
        movieDao.getLastMovies()
        return true
    }

    override suspend fun readFilmsFromDB(): ArrayList<FilmDB> {
        TODO("Not yet implemented")
    }

    override suspend fun clearDB(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun onClickFilm() {
        TODO("Not yet implemented")
    }

    override suspend fun toDBEntity(films: MutableList<Film>): List<FilmDB> {
        val filmsDBList: MutableList<FilmDB> = mutableListOf()
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
        return filmsDBList.toList()
    }

    override suspend fun fromDBEntity(filmsDB: MutableList<FilmDB>): List<Film> {
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

//    override fun clickFilm(film: Film) {
//        val intent = Intent(this, WikiActivity::class.java)
//        intent.putExtra("id", film.id).putExtra("image", film.image)
//        startActivity(intent)
//    }



}