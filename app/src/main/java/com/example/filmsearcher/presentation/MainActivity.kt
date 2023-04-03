package com.example.filmsearcher.presentation

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmsearcher.data.repository.FilmSearcherApiService
import com.example.filmsearcher.data.repository.FromDBEntityImpl
import com.example.filmsearcher.data.repository.ToDBEntityImpl
import com.example.filmsearcher.databinding.ActivityMainBinding
import com.example.filmsearcher.domain.models.Film
import com.example.filmsearcher.domain.repository.FilmSearcherRepositoryImpl
import com.example.filmsearcher.domain.usecase.FromDBEntity
import com.example.filmsearcher.domain.usecase.GetFilms
import com.example.filmsearcher.domain.usecase.ToDBEntity
import com.example.filmsearcher.old.rcView.FilmAdapter
import com.example.filmsearcher.old.rcView.FilmClicker
import com.example.filmsearcher.old.room.dataBase.MovieDB.Companion.getDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), FilmClicker {

    private lateinit var binding: ActivityMainBinding
    private var listOfFilms: ArrayList<Film> = arrayListOf()
    private var adapter = FilmAdapter(listOfFilms, this)

    private val converterToDB = ToDBEntityImpl()
    private val toDBEntity = ToDBEntity(converterToDB)

    private val converterFromDB = FromDBEntityImpl()
    private val fromDBEntity = FromDBEntity(converterFromDB)

//    private val viewModel = MainActivityViewModel(
//        FilmSearcherRepositoryImpl(
//            FilmSearcherApiService()
//        )
//    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myDB = getDB(this)
        init()
//        viewModel.apiErrorLiveData.observe(this, Observer {
//
//        })
//
//        viewModel.filmsListLiveData.observe(this, Observer {
//
//        })


//         получить из базы список фильмов, конвертировать и отрисовать
//        CoroutineScope(Dispatchers.IO).launch {
//            listOfFilms = fromDBEntity.execute(myDB.movieDao().getLastMovies())
//            runOnUiThread {
//            init()
//
//            }


        // сделать запрос в апишку, сгенерировать список
        // очистить БД, конвертировать новый список и записать его в БД
        // отрисовать по новому запросу
        binding.bSeacrh.setOnClickListener() {
            CoroutineScope(Dispatchers.IO).launch {

                val response = getFilmsCase.execute("${binding.etSearch.text}")
                listOfFilms.clear()
                for (i in response.results) listOfFilms.add(i)
                myDB.movieDao().deleteAll()  //deleteAll.execute(myDB)
                myDB.movieDao()
                    .insertAllMovies(toDBEntity.execute(listOfFilms)) //insertAll.execute(myDB)

            }

//            runOnUiThread {
//                init()
//                // это тоже перенести
//            }

        }
    }


//        val isConnectedToInternet = isInternetConnected(applicationContext)
//        if (isConnectedToInternet) {
//            CoroutineScope(Dispatchers.IO).launch{
//                myDB.movieDao().deleteAll()
//            }
//        } else {
//            CoroutineScope(Dispatchers.IO).launch {
//                listOfFilms = fromEntity(myDB.movieDao().getLastMovies())
//                adapter = FilmAdapter(listOfFilms, this@MainActivity)
//                init()
//            }
//        }


    private fun init() {
        isInternetConnected(context = this)
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter
        }
    }

    private fun isInternetConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return (activeNetworkInfo != null) && activeNetworkInfo.isConnected
    }

    override fun clickFilm(film: Film) {
        val intent = Intent(this, WikiActivity::class.java)
        intent.putExtra("id", film.id).putExtra("image", film.image)
        startActivity(intent)
    }
}