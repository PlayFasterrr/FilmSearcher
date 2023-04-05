package com.example.filmsearcher.di

import com.example.filmsearcher.presentation.MainActivityViewModel
import com.example.filmsearcher.presentation.WikiActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel() {
        MainActivityViewModel(filmsRepo = get())
    }

    viewModel() {
        WikiActivityViewModel(filmsRepo = get())
    }
}
