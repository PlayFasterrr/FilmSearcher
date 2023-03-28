package com.example.filmsearcher.dataClass

import com.google.gson.annotations.SerializedName

data class JsonResponse (

    @SerializedName("searchType"   ) var searchType   : String?            = null,
    @SerializedName("expression"   ) var expression   : String?            = null,
    @SerializedName("results"      ) var results      : ArrayList<Film> = arrayListOf(),
    @SerializedName("errorMessage" ) var errorMessage : String?            = null

)


//data class Film(
//    val id: String?,
//    val resultType: String?,
//    val image :String?,
//    val title: String?,
//    val description: String?) {
//}

data class Film (

    @SerializedName("id"          ) var id          : String? = null,
    @SerializedName("resultType"  ) var resultType  : String? = null,
    @SerializedName("image"       ) var image       : String? = null,
    @SerializedName("title"       ) var title       : String? = null,
    @SerializedName("description" ) var description : String? = null

)