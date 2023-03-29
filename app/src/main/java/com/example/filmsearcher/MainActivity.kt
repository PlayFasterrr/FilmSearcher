package com.example.filmsearcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmsearcher.dataClass.Film
import com.example.filmsearcher.databinding.ActivityMainBinding
import com.example.filmsearcher.rcView.FilmAdapter
import com.example.filmsearcher.retrofit.FilmApi
import com.example.filmsearcher.room.dataBase.MovieDB.Companion.getDB
import com.example.filmsearcher.room.entity.FilmsDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var listOfFilms: ArrayList<Film> = arrayListOf()
    private var adapter = FilmAdapter(listOfFilms)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val apiService = FilmApi.create()
        val myDB = getDB(this)

        CoroutineScope(Dispatchers.IO).launch {
            listOfFilms = fromEntity(myDB.movieDao().getLastMovies())
            adapter = FilmAdapter(listOfFilms)
            init()
        }

        binding.bSeacrh.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val result = apiService.getFilms("${binding.etSearch.text}")
                listOfFilms.clear()
                for (i in result.results) {
                    listOfFilms.add(i)
                }
                myDB.movieDao().deleteAll()
                val entities = toEntity(listOfFilms)
                myDB.movieDao().insertAllMovies(entities)

                runOnUiThread {
                    init()
                }
            }
        }
    }

    private fun init() {
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter
        }
    }

    private fun toEntity(list: ArrayList<Film>): ArrayList<FilmsDB> {
        val filmsDBList: ArrayList<FilmsDB> = arrayListOf()
        for (film in list) {
            val filmsDB = FilmsDB(
                id = film.id ?: "1",
                resultType = film.resultType ?: "",
                image = film.image ?: "",
                title = film.title ?: "",
                description = film.description ?: ""
            )
            filmsDBList.add(filmsDB)
        }
        return filmsDBList
    }

    private fun fromEntity(list: List<FilmsDB>): ArrayList<Film> {
        val filmsList: ArrayList<Film> = arrayListOf()
        for (item in list) {
            val movie = Film(
                id = item.id,
                resultType = item.resultType,
                image = item.image,
                title = item.title,
                description = item.description
            )
            filmsList.add(movie)
        }
        return filmsList
    }
}