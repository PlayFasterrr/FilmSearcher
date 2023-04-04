package com.example.filmsearcher.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmsearcher.R
import com.example.filmsearcher.data.repository.FilmSearcherRepositoryImpl
import com.example.filmsearcher.data.room.dataBase.FilmsDataBase
import com.example.filmsearcher.databinding.ActivityWikiBinding
import com.example.filmsearcher.domain.repository.FilmSearcherApiService
import com.squareup.picasso.Picasso

class WikiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWikiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWikiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myDB = FilmsDataBase.getDB(this@WikiActivity)
        val viewModel = WikiActivityViewModel(
            FilmSearcherRepositoryImpl(
                FilmSearcherApiService.create(),
                myDB.movieDao()
            )
        )

        val request = intent.getStringExtra("id") ?: "terrible mistake"
        var image = intent.getStringExtra("image")

        viewModel.getResponseWiki(request = request)

        viewModel.responseWikiApi.observe(this) { response ->
            binding.apply {
                tvFilmTitle.text = response.title
                tvFilmYear.text = response.year
                tvPlotShort.text = response.plotShort?.plainText.toString()
                if (image == "" || image == null) image =
                    R.drawable.ic_launcher_foreground.toString()
                Picasso
                    .get()
                    .load(image)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(ivFilmImage)

                bBack.setOnClickListener {
                    finish()
                }
            }
        }
    }
}