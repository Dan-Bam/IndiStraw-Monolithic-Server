package com.project.indistraw.domain.movie.application.port.input.dto

data class DirectorIdDto(
    val idx: Long,
    val name: String,
    val profileUrl: String,
    val movieList: List<MovieDto>?
)