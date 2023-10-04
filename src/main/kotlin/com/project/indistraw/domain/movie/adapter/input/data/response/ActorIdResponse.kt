package com.project.indistraw.domain.movie.adapter.input.data.response

import com.project.indistraw.domain.movie.application.port.input.dto.MovieDto

data class ActorIdResponse(
    val idx: Long,
    val name: String,
    val profileUrl: String,
    val movieList: List<MovieDto>?
)