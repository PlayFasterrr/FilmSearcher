package com.example.filmsearcher.data.repository

import com.example.filmsearcher.domain.models.WikiResponse
import com.example.filmsearcher.domain.repository.WikiApi
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface WikiApiImpl : WikiApi {

    @GET("en/API/Wikipedia/k_d7x2gnsg/{expression}")
    override suspend fun getFilmWiki(
        @Path("expression") id: String
    ): WikiResponse

    companion object Factory {

        fun create(): WikiApiImpl {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://imdb-api.com/")
                .build()

            return retrofit.create(WikiApiImpl::class.java)
        }
    }
}
