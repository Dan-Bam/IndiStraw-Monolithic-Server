package com.project.indistraw.domain.movie.application.port.input.dto

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre
import com.project.indistraw.domain.movie.domain.Actor
import com.project.indistraw.domain.movie.domain.Director

data class MovieDetailDto(
    val title: String,
    val description: String,
    val movieUrl: String,
    val thumbnailUrl: String,
    val director: List<Director?>,
    val actor: List<Actor?>,
    val movieHighlight: List<String>,
    val crowdTrue: Boolean,
    val genre: Genre
)