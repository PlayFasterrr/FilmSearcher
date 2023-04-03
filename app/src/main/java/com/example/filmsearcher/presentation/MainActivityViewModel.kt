package com.example.filmsearcher.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsearcher.domain.models.Film
import com.example.filmsearcher.domain.repository.FilmSearcherRepository
import com.example.filmsearcher.domain.usecase.GetFilms
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import kotlin.math.exp

class MainActivityViewModel(
    val filmsRepo: FilmSearcherRepository
): ViewModel() {

    val filmsListLiveData = MutableLiveData<List<Film>>()
    val apiErrorLiveData = MutableLiveData<String>()

    fun getFilms(expression: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = filmsRepo.getFilms(expression)
                if(response.isSuccessful) {
                    filmsListLiveData.postValue(response.body()?.results)
                }
            } catch (e: UnknownHostException) {
                apiErrorLiveData.postValue("Check your connection!")
            } catch (e: java.lang.Exception) {
                apiErrorLiveData.postValue("Something went wrong!")
            }
        }
    }

}