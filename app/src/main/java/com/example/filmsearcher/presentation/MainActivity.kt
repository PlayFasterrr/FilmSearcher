package com.example.filmsearcher.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.filmsearcher.data.models.Film
import com.example.filmsearcher.data.repository.FilmAdapter
import com.example.filmsearcher.databinding.ActivityMainBinding
import com.example.filmsearcher.domain.repository.FilmClicker
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), FilmClicker {

    private lateinit var binding: ActivityMainBinding
    private var adapter = FilmAdapter(this)
    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!viewModel.checkInternetConnection(this)) viewModel.drawFromDB()

        drawRCView()

        viewModel.filmsListLiveData.observe(this) {
            adapter.updateList(it)
        }

        viewModel.loadLiveData.observe(this) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        binding.bSeacrh.setOnClickListener {
            viewModel.getFilms(binding.etSearch.text.toString())
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
        }
    }

    private fun drawRCView() {
        binding.rcView.adapter = adapter
    }

    override fun clickFilm(film: Film) {
        val intent = Intent(this, WikiActivity::class.java)
        intent.putExtra("id", film.id).putExtra("image", film.image)
        startActivity(intent)
    }
}