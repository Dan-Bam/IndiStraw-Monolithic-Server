package com.project.indistraw.domain.movie.application.port.input.dto

data class UpdateMovieDto(
    val title: String,
    val description: String,
    val movieUrl: String,
    val thumbnailUrl: String,
    val movieHighlight: List<String>,
    val director: List<Long>,
    val actor: List<Long>,
    val crowdTrue: Boolean
)