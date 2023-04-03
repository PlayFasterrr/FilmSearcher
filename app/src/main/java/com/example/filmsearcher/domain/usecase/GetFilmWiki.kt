package com.example.filmsearcher.domain.usecase

import com.example.filmsearcher.domain.models.WikiResponse

class GetFilmWiki(private val wikiApi: WikiApi) {

    suspend fun execute(id: String): WikiResponse {
        return wikiApi.getFilmWiki(id = id)
    }
}