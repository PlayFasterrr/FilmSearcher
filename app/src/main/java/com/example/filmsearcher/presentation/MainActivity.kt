package com.example.filmsearcher.presentation

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmsearcher.data.models.Film
import com.example.filmsearcher.data.repository.FilmAdapter
import com.example.filmsearcher.data.repository.FilmSearcherRepositoryImpl
import com.example.filmsearcher.data.room.dataBase.FilmsDataBase.Companion.getDB
import com.example.filmsearcher.databinding.ActivityMainBinding
import com.example.filmsearcher.domain.repository.FilmClicker
import com.example.filmsearcher.domain.repository.FilmSearcherApiService

class MainActivity : AppCompatActivity(), FilmClicker {

    private lateinit var binding: ActivityMainBinding
    private var listOfFilms: List<Film>? = listOf()
    private var adapter = FilmAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myDB = getDB(this@MainActivity)

        val viewModel = MainActivityViewModel (
            FilmSearcherRepositoryImpl(
                FilmSearcherApiService.create(),
                myDB.movieDao()
            )
        )

        init()
        viewModel.filmsListLiveData.observe(this, androidx.lifecycle.Observer<List<Film>?>{
            adapter.updateList(it)
        })

        binding.bSeacrh.setOnClickListener{
            viewModel.getFilms(binding.etSearch.text.toString())
            Log.d("LOGGA", " after click$listOfFilms")
        }
    }

    private fun init() {
        binding.rcView.adapter = adapter
    }

    override fun clickFilm(film: Film) {
        val intent = Intent(this, WikiActivity::class.java)
        intent.putExtra("id", film.id).putExtra("image", film.image)
        startActivity(intent)
    }
    private fun isInternetConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return (activeNetworkInfo != null) && activeNetworkInfo.isConnected
    }
}