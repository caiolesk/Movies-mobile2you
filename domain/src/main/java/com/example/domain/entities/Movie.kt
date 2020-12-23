package com.example.domain.entities

import java.io.Serializable

data class Movie(
    var id: Int?,
    var title: String?,
    var original_title: String?,
    var overview: String?,
    var popularity: Number?,
    var backdrop_path: String?,
    var poster_path: String?,
    var release_date: String?,
    var vote_count: Int?,
    var vote_average: Number?,
    var genre_ids: ArrayList<Int>?
) : Serializable {
    var genre_names: List<String>? = null
}