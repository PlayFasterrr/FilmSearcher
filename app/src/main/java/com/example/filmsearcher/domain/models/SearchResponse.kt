package com.example.filmsearcher.domain.models

import com.example.filmsearcher.old.room.entity.FilmsDB
import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @SerializedName("searchType") var searchType: String? = null,
    @SerializedName("expression") var expression: String? = null,
    @SerializedName("results") var results: List<Film> = listOf(),
    @SerializedName("errorMessage") var errorMessage: String? = null

)

data class Film(

    @SerializedName("id") var id: String? = null,
    @SerializedName("resultType") var resultType: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null

) {
//    fun fromEntity(): FilmsDB {
//        return FilmsDB(
//            id = this.id,
//            resultType = this.resultType
//        )
//    }
}