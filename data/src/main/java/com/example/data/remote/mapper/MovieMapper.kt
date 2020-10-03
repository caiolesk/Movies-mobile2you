package com.example.data.remote.mapper

import com.example.data.remote.model.MoviePayload
import com.example.data.remote.model.MoviesPayload
import com.example.domain.entities.Movie

object MovieMapper {

    fun map(payload: MoviesPayload) = payload.moviesPayload.map { map(it) }

    fun map(payload: MoviePayload) = Movie(
        id = payload.id,
        title = payload.title,
        original_title = payload.original_title,
        popularity = payload.popularity,
        backdrop_path = payload.backdrop_path,
        poster_path = payload.poster_path,
        release_date = payload.release_date,
        vote_count = payload.vote_count,
        vote_average = payload.vote_average
    )

}