package com.example.filmsearcher.old.room.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.filmsearcher.data.repository.dataBase.MovieDao
import com.example.filmsearcher.old.room.entity.FilmsDB

@Database(version = 1, entities = [FilmsDB::class], exportSchema = false)
abstract class MovieDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDB? = null

        fun getDB(context: Context): MovieDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDB::class.java,
                    "movie_db"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}