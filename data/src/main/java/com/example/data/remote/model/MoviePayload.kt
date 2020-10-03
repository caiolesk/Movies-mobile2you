package com.example.data.remote.model

import com.google.gson.annotations.SerializedName

class MoviesPayload(
    @SerializedName("results") val moviesPayload: List<MoviePayload>
)

class MoviePayload(
    @SerializedName("id") var id: Int?,
    @SerializedName("title") var title: String?,
    @SerializedName("original_title") var original_title: String?,
    @SerializedName("popularity")var popularity: Number?,
    @SerializedName("backdrop_path")var backdrop_path: String?,
    @SerializedName("poster_path")var poster_path: String?,
    @SerializedName("release_date")var release_date: String?,
    @SerializedName("vote_count")var vote_count: Int?,
    @SerializedName("vote_average")var vote_average: Number?
)