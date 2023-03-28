package com.example.filmsearcher.retrofit

import com.example.filmsearcher.dataClass.JsonResponse
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmApi {
    @GET ("en/API/SearchMovie/k_d7x2gnsg/{expression}")
    suspend fun getFilms(
        @Path("expression") expression : String
    ) : JsonResponse

    companion object Factory {

        fun create(): FilmApi{
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://imdb-api.com/")
                .build()

            return retrofit.create(FilmApi::class.java)
        }
    }
}