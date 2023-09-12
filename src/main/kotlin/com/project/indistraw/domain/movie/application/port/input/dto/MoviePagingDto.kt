package com.project.indistraw.domain.movie.application.port.input.dto

data class MoviePagingDto(
    val isLate: Boolean,
    val list: List<MovieDto>
)