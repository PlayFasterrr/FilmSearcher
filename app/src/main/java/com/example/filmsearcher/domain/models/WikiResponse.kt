package com.example.filmsearcher.domain.models

import com.google.gson.annotations.SerializedName

data class WikiResponse(

    @SerializedName("imDbId") var imDbId: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("fullTitle") var fullTitle: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("year") var year: String? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("titleInLanguage") var titleInLanguage: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("plotShort") var plotShort: PlotShort? = PlotShort(),
    @SerializedName("plotFull") var plotFull: PlotFull? = PlotFull(),
    @SerializedName("errorMessage") var errorMessage: String? = null
)

data class PlotShort(

    @SerializedName("plainText") var plainText: String? = null,
    @SerializedName("html") var html: String? = null

)

data class PlotFull(

    @SerializedName("plainText") var plainText: String? = null,
    @SerializedName("html") var html: String? = null

)