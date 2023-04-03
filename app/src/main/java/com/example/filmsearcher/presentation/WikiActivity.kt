package com.example.filmsearcher.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmsearcher.databinding.ActivityWikiBinding

class WikiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWikiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWikiBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val apiService = WikiApiImpl.create()
//        val request = intent.getStringExtra("id")
//        var image = intent.getStringExtra("image")
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val result = apiService.getFilmWiki("$request")
//
//            runOnUiThread {
//                binding.apply {
//                    tvFilmTitle.text = result.title
//                    tvFilmYear.text = result.year
//                    tvPlotShort.text = result.plotShort?.plainText.toString()
//                    if (image == "" || image == null) image =
//                        R.drawable.ic_launcher_foreground.toString()
//                    Picasso
//                        .get()
//                        .load(image)
//                        .error(R.drawable.ic_launcher_foreground)
//                        .into(ivFilmImage)
//                    bBack.setOnClickListener {
//                        finish()
//                    }
//                }
//            }
//        }
    }
}