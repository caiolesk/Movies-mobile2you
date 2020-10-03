package com.example.data.remote.mapper

import com.example.data.remote.model.GenresListPayload
import com.example.data.remote.model.GenresPayload
import com.example.domain.entities.Genres

object GenresMapper {

    fun map(payload: GenresListPayload) = payload.genresListPayload.map { map(it) }

    fun map(payload: GenresPayload) = Genres(
        id = payload.id,
        name = payload.name
    )
}