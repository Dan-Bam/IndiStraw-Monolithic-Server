package com.project.indistraw.domain.movie.adapter.input.data.response

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre
import com.project.indistraw.domain.movie.domain.Actor
import com.project.indistraw.domain.movie.domain.Director

data class MovieDetailResponse(
    val title: String,
    val description: String,
    val movieUrl: String,
    val thumbnailUrl: String,
    val director: List<Director?>,
    val actor: List<Actor?>,
    val movieHighLight: List<String>,
    val clowdTrue: Boolean,
    val genre: Genre
)