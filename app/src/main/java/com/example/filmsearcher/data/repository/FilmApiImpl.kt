package com.example.filmsearcher.data.repository

import com.example.filmsearcher.domain.models.SearchResponse
import com.example.filmsearcher.domain.repository.FilmApi
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmApiImpl : FilmApi {
    @GET("en/API/SearchMovie/k_d7x2gnsg/{expression}")
    override suspend fun getFilms(
        @Path("expression") expression: String
    ): SearchResponse

    companion object Factory {

        fun create(): FilmApiImpl {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://imdb-api.com/")
                .build()

            return retrofit.create(FilmApiImpl::class.java)
        }
    }
}