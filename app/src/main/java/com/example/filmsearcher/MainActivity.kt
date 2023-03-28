package com.example.filmsearcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmsearcher.dataClass.Film
import com.example.filmsearcher.databinding.ActivityMainBinding
import com.example.filmsearcher.rcView.FilmAdapter
import com.example.filmsearcher.retrofit.FilmApi
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
        binding.bSeacrh.setOnClickListener() {

            CoroutineScope(Dispatchers.IO).launch {
                val result = apiService.getFilms("${binding.etSearch.text}")
                listOfFilms.clear()
                for (i in result.results) {
                    listOfFilms.add(i)
                }
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
}