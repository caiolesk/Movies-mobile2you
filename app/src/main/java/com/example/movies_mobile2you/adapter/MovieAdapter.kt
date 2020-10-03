package com.example.movies_mobile2you.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Movie
import com.example.movies_mobile2you.R
import com.example.movies_mobile2you.extension.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_movie_similar.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var movies: List<Movie> = listOf()

    inner class ViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(parent.inflate(R.layout.adapter_movie_similar)){

        fun bind(movie: Movie) = with(itemView){
            var urlBase = resources.getString(R.string.url_base_image_similar)

            Picasso.get()
                .load(urlBase + movie.poster_path)
                .into(imageMovieSimilar)
            textTitleMovieSimilar.text = movie.title
            textAgeMovie.text = movie.release_date?.split("-")?.get(0) ?: ""

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position])

}