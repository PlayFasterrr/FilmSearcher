package com.example.filmsearcher.domain.repository

import com.example.filmsearcher.domain.models.WikiResponse

interface WikiApi {
    suspend fun getFilmWiki(id: String): WikiResponse
}