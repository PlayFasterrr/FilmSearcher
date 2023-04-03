package com.example.filmsearcher.data.repository

import com.example.filmsearcher.domain.models.SearchResponse
import com.example.filmsearcher.domain.models.WikiResponse
import retrofit2.Response
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmSearcherApiService {

    @GET("en/API/Wikipedia/k_d7x2gnsg/{expression}")
    suspend fun getFilmWiki(
        @Path("expression") id: String
    ): Response<WikiResponse>

    @GET("en/API/SearchMovie/k_d7x2gnsg/{expression}")
    suspend fun getFilms(
        @Path("expression") expression: String
    ): Response<SearchResponse>

    companion object Factory {

        fun create(): FilmSearcherApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://imdb-api.com/")
                .build()

            return retrofit.create(FilmSearcherApiService::class.java)
        }
    }
}
