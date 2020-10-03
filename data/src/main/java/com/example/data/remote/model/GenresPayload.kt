package com.example.data.remote.model

import com.google.gson.annotations.SerializedName

class GenresListPayload(
    @SerializedName("genres") val genresListPayload: List<GenresPayload>
)

class GenresPayload(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String
)