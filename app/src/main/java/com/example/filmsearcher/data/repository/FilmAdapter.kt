package com.example.filmsearcher.data.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.filmsearcher.R
import com.example.filmsearcher.databinding.FilmCardBinding
import com.example.filmsearcher.data.models.Film
import com.example.filmsearcher.domain.repository.FilmClicker
import com.squareup.picasso.Picasso

class FilmAdapter(
    private val filmClicker: FilmClicker
) : RecyclerView.Adapter<FilmAdapter.FilmHolder>() {

    private var filmsToAdd: List<Film>? = listOf()

    class FilmHolder(itemView: View) : ViewHolder(itemView) {
        private val binding = FilmCardBinding.bind(itemView)

        fun bind(film: Film, filmClicker: FilmClicker) {

            binding.apply {
                tvFilmTitle.text = film.title
                tvFilmDescription.text = film.description
                itemView.setOnClickListener {
                    filmClicker.clickFilm(film)
                }
                if (film.image == "" || film.image == null) film.image =
                    R.drawable.ic_launcher_foreground.toString()
                Picasso
                    .get()
                    .load(film.image)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(ivFilmImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.film_card, parent, false)
        return FilmHolder(view)
    }

    override fun getItemCount(): Int {
        return filmsToAdd?.size ?: 0
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        val film: Film = filmsToAdd?.get(position) ?: Film()
        holder.bind(film, filmClicker)
    }

    fun updateList(filmsList: List<Film>?) {
        filmsToAdd = filmsList
        notifyDataSetChanged()
    }
}