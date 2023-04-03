package com.example.filmsearcher.old.room.entity

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@androidx.room.Entity("films")
data class FilmsDB(

    @PrimaryKey @SerializedName("id") var id: String,
    @SerializedName("resultType") var resultType: String,
    @SerializedName("image") var image: String,
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String

)