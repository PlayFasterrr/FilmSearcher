package com.example.filmsearcher.data.room.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.filmsearcher.data.room.dao.FilmsDao
import com.example.filmsearcher.data.room.entity.FilmDB

@Database(version = 1, entities = [FilmDB::class], exportSchema = false)
abstract class FilmsDataBase : RoomDatabase() {
    abstract fun movieDao(): FilmsDao

    companion object {
        @Volatile
        private var INSTANCE: FilmsDataBase? = null

        fun getDB(context: Context): FilmsDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FilmsDataBase::class.java,
                    "movie_db"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}