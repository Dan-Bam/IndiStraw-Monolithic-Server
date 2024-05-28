package com.project.indistraw.domain.movie.application.port.input.dto

data class MoviePagingDto(
    val last: Boolean,
    val list: List<MovieDto>
)
{
    constructor(): this(false, listOf())
}