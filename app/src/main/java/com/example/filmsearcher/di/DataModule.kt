package com.example.filmsearcher.di

import com.example.filmsearcher.data.repository.FilmSearcherRepositoryImpl
import com.example.filmsearcher.data.room.dao.FilmsDao
import com.example.filmsearcher.data.room.dataBase.FilmsDataBase
import com.example.filmsearcher.domain.repository.FilmSearcherApiService
import com.example.filmsearcher.domain.repository.FilmSearcherRepository
import org.koin.dsl.module

val dataModule = module {

    single<FilmSearcherRepository> {
        FilmSearcherRepositoryImpl(apiService = get(), movieDao = get())
    }

    single<FilmSearcherApiService> { FilmSearcherApiService.create() }

    single<FilmsDao> { FilmsDataBase.getDB(context = get()).movieDao() }

}