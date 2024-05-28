package com.project.indistraw.domain.movie.adapter.input.data.response

data class MoviePagingResponse(
    val last: Boolean,
    val list: List<MovieResponse>
)