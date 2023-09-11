package com.project.indistraw.domain.movie.application.port.input.dto

data class CreateMovieDto(
    val title: String,
    val description: String,
    val movieUrl: String,
    val thumbnailUrl: String,
    val movieHighlight: List<String>,
    val director: List<Int>,
    val actor: List<Int>,
    val clowdTrue: Boolean
)