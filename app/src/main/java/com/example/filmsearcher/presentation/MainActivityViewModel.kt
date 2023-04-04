package com.example.filmsearcher.presentation

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsearcher.data.models.Film
import com.example.filmsearcher.domain.repository.FilmSearcherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainActivityViewModel(
    private val filmsRepo: FilmSearcherRepository
) : ViewModel() {

    var filmsListLiveData = MutableLiveData<List<Film>?>()
    val loadLiveData = MutableLiveData<Boolean>()
    private val apiErrorLiveData = MutableLiveData<String?>()

    fun getFilms(expression: String) {
        loadLiveData.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = filmsRepo.getFilmsFromAPI(expression)
                if (response.isSuccessful) {
                    loadLiveData.postValue(false)
                    filmsListLiveData.postValue(response.body()?.results)
                    filmsRepo.clearDB()
                    filmsRepo.insertFilmsToDB(filmsListLiveData.value)

                }
            } catch (e: UnknownHostException) {
                loadLiveData.postValue(false)
                apiErrorLiveData.postValue("Check your connection!")
            } catch (e: Exception) {
                loadLiveData.postValue(false)
                apiErrorLiveData.postValue("Something went wrong!")
            }
        }
    }

    fun drawFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            filmsListLiveData.postValue(filmsRepo.readFilmsFromDB())
        }
    }

    fun checkInternetConnection(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}