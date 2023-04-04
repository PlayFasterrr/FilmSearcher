package com.example.filmsearcher.data.room.entity

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@androidx.room.Entity("films")
data class FilmDB(

    @PrimaryKey @SerializedName("id") var id: String,
    @SerializedName("resultType") var resultType: String,
    @SerializedName("image") var image: String,
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String

)