package com.project.indistraw.domain.movie.application.port.input.dto

data class ActorIdDto(
    val id: Int,
    val name: String,
    val profileUrl: String,
    val movieList: List<MovieDto>?
)