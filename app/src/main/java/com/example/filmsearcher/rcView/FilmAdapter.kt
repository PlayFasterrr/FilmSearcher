package com.example.filmsearcher.rcView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.filmsearcher.R
import com.example.filmsearcher.databinding.FilmCardBinding
import com.example.filmsearcher.dataClass.Film
import com.squareup.picasso.Picasso

class FilmAdapter(private val filmsToAdd: ArrayList<Film>) :
    RecyclerView.Adapter<FilmAdapter.FilmHolder>() {

    class FilmHolder(itemView: View) : ViewHolder(itemView) {
        private val binding = FilmCardBinding.bind(itemView)

        fun bind(film: Film) {

            binding.apply {
                tvFilmTitle.text = film.title
                tvFilmDescription.text = film.description
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
        return filmsToAdd.size
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        val film: Film = filmsToAdd[position]
        holder.bind(film)
    }
}