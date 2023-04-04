package com.example.filmsearcher.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsearcher.data.models.Film
import com.example.filmsearcher.data.repository.FilmSearcherRepositoryImpl
import com.example.filmsearcher.domain.repository.FilmSearcherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainActivityViewModel(
    private val filmsRepo: FilmSearcherRepository
) : ViewModel() {


    var filmsListLiveData = MutableLiveData<List<Film>?>()

    //    Livedata for displaying load state
    val loadLivedata = MutableLiveData<Boolean>()
    var liveData = filmsListLiveData.value

    private val apiErrorLiveData = MutableLiveData<String?>()

    fun getFilms(expression: String) {
        loadLivedata.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = filmsRepo.getFilmsFromAPI(expression)
                if (response.isSuccessful) {
                    loadLivedata.postValue(false)
                    filmsListLiveData.postValue(response.body()?.results)
                    Log.d("LOGGA", " from VM ${response.body()?.results}")
                    Log.d("LOGGA", " from VM live ${filmsListLiveData.value}")

                }
            } catch (e: UnknownHostException) {
                loadLivedata.postValue(false)
                apiErrorLiveData.postValue("Check your connection!")
            } catch (e: Exception) {
                loadLivedata.postValue(false)
                apiErrorLiveData.postValue("Something went wrong!")
            }
        }
    }
}