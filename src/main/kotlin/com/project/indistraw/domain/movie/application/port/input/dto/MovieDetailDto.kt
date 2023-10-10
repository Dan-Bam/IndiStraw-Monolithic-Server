package com.project.indistraw.domain.movie.application.port.input.dto

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre

data class MovieDetailDto(
    val title: String,
    val description: String,
    val movieUrl: String,
    val thumbnailUrl: String,
    val director: List<DirectorDto>,
    val actor: List<ActorDto>,
    val movieHighlight: List<String>,
    val crowdTrue: Boolean,
    val genre: Genre
)