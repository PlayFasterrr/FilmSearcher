package com.example.filmsearcher.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmsearcher.R
import com.example.filmsearcher.databinding.ActivityWikiBinding
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class WikiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWikiBinding
    private val viewModel by viewModel<WikiActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWikiBinding.inflate(layoutInflater)
        setContentView(binding.root)

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