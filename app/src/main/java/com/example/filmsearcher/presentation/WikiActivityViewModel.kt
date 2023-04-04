package com.example.filmsearcher.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsearcher.data.models.WikiResponse
import com.example.filmsearcher.domain.repository.FilmSearcherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class WikiActivityViewModel(
    private val filmsRepo: FilmSearcherRepository
) : ViewModel() {

    val responseWikiApi = MutableLiveData<WikiResponse>()
    private val wikiApiErrorLiveData = MutableLiveData<String?>()

    fun getResponseWiki(request: String) {
        if (request == "terrible mistake") {
            responseWikiApi.setValue(WikiResponse(title = "Bad request"))
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = filmsRepo.getFilmWikiFromAPI(request)
                    if (response.isSuccessful) {
                        responseWikiApi.postValue(response.body())
                    }
                } catch (e: UnknownHostException) {
                    wikiApiErrorLiveData.postValue("Check your connection!")
                } catch (e: Exception) {
                    wikiApiErrorLiveData.postValue("Something went wrong!")
                }
            }
        }
    }
}