package com.example.filmsearcher.data.models

import com.example.filmsearcher.data.room.entity.FilmDB
import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @SerializedName("searchType") var searchType: String? = null,
    @SerializedName("expression") var expression: String? = null,
    @SerializedName("results") var results: List<Film>? = listOf(),
    @SerializedName("errorMessage") var errorMessage: String? = null

)

data class Film(

    @SerializedName("id") var id: String? = null,
    @SerializedName("resultType") var resultType: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null

)