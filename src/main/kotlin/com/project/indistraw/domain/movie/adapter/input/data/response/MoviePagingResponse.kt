package com.project.indistraw.domain.movie.adapter.input.data.response

data class MoviePagingResponse(
    val isLast: Boolean,
    val list: List<MovieResponse>
)