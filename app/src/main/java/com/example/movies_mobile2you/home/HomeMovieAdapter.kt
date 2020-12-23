package com.example.movies_mobile2you.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import com.example.movies_mobile2you.R
import com.example.movies_mobile2you.databinding.AdapterMovieSimilarBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class HomeMovieAdapter @Inject constructor() : RecyclerView.Adapter<HomeMovieAdapter.ViewHolder>() {

    var genres: List<Genres> = listOf()

    var movies = mutableListOf<Movie>()
        set(value) {
            field = setItems(value)
            notifyDataSetChanged()
        }

    var onClick: ((Movie) -> Unit)? = null

    private fun setItems(movies: MutableList<Movie>): MutableList<Movie> {
        movies.forEach { movie ->
            val genresLinked =
                genres.filter { m -> movie.genre_ids?.any { it == m.id }!! }.map { c -> c.name }
            movie.genre_names = genresLinked
        }

        return movies
    }

    inner class ViewHolder(private val binding: AdapterMovieSimilarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) = with(itemView) {
            val urlBase = resources.getString(R.string.url_base_image_similar)

            Picasso.get()
                .load(urlBase + movie.poster_path)
                .into(binding.imageMovieSimilar)
            binding.textTitleMovieSimilar.text = movie.title
            binding.textAgeMovie.text = movie.release_date?.split("-")?.get(0) ?: ""
            binding.textGenres.text = movie.genre_names?.joinToString(", ")

            binding.cardMovieAdapter.setOnClickListener {
                onClick?.invoke(movies[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMovieSimilarBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position])

}